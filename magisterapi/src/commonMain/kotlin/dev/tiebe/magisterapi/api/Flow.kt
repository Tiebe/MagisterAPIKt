@file:Suppress("unused")

package dev.tiebe.magisterapi.api

import dev.tiebe.magisterapi.utils.MagisterException
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

private val client = HttpClient {
    install(ContentNegotiation) {
        json(json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

suspend fun requestPOST(
    url: Url,
    body: HashMap<String, String> = hashMapOf(),
    accessToken: String? = null,
    retries: Int = 0
): HttpResponse {
    val response = client.submitForm(url.toString(),
        formParameters = Parameters.build {
            for ((key, value) in body) {
                append(key, value)
            }
        }
    ) {
        if (accessToken != null) {
            bearerAuth(accessToken)
        }
    }

    if (checkResponseStatus(response.status, response, retries)) return requestPOST(url, body, accessToken, retries + 1)

    return response
}

suspend fun requestPOST(
    url: Url,
    requestBody: Any,
    accessToken: String? = null,
    retries: Int = 0
): HttpResponse {
    val response = client.post(url.toString()) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)

        if (accessToken != null) {
            bearerAuth(accessToken)
        }
    }

    if (checkResponseStatus(response.status, response, retries)) return requestPOST(url, requestBody, accessToken, retries + 1)

    return response
}

suspend fun requestGET(
    url: Url,
    body: HashMap<String, String> = hashMapOf(),
    accessToken: String? = null,
    retries: Int = 0
): HttpResponse {
    try {
        val response = client.get(url) {
            url {
                parameters.apply {
                    for ((key, value) in body) {
                        append(key, value)
                    }
                }
            }

            if (accessToken != null) {
                bearerAuth(accessToken)
            }
        }

        if (checkResponseStatus(response.status, response, retries)) return requestGET(url, body, accessToken, retries + 1)

        return response
    } catch (e: IllegalArgumentException) {
        if (e.message?.contains("text is empty") == true) {
            return requestGET(url, body, accessToken, retries + 1)
        } else {
            throw e
        }
    }
}

suspend fun requestPATCH(
    url: Url,
    requestBody: Any,
    accessToken: String? = null,
    retries: Int = 0
): HttpResponse {
    val response = client.patch(url.toString()) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)

        if (accessToken != null) {
            bearerAuth(accessToken)
        }
    }

    if (checkResponseStatus(response.status, response, retries)) return requestPATCH(url, requestBody, accessToken, retries + 1)

    return response
}

suspend fun requestDELETE(
    url: Url,
    requestBody: Any,
    accessToken: String? = null,
    retries: Int = 0
): HttpResponse {
    val response = client.delete(url.toString()) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)

        if (accessToken != null) {
            bearerAuth(accessToken)
        }
    }

    if (checkResponseStatus(response.status, response, retries)) return requestDELETE(url, requestBody, accessToken, retries + 1)

    return response
}

private suspend fun checkResponseStatus(
    it: HttpStatusCode,
    response: HttpResponse,
    retries: Int
): Boolean {
    if (it != HttpStatusCode.OK && it != HttpStatusCode.NoContent) {
        if (response.bodyAsText().contains("<HTML><HEAD>") && retries < 30) {
            delay(2000 * (retries + 1).toLong())

            return true
        } else {
            throw MagisterException(
                response.status, response.bodyAsText(),
                "Request failed with status code ${it.value} with message ${response.bodyAsText()}"
            )
        }
    }
    return false
}