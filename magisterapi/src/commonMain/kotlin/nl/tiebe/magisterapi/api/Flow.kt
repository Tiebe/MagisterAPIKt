package nl.tiebe.magisterapi.api

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import nl.tiebe.magisterapi.utils.MagisterException

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
    accessToken: String? = null
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

    response.status.let {
        if (it != HttpStatusCode.OK) {
            throw MagisterException(response.status, response.bodyAsText(),
                "Request failed with status code ${it.value} with message ${response.bodyAsText()}")
        }
    }

    return response
}

suspend fun requestPOST(
    url: Url,
    requestBody: Any,
    accessToken: String? = null
): HttpResponse {


    val response = client.post(url.toString()) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)

        if (accessToken != null) {
            bearerAuth(accessToken)
        }
    }

    response.status.let {
        if (it != HttpStatusCode.OK) {
            throw MagisterException(response.status, response.bodyAsText(),
                "Request failed with status code ${it.value} with message ${response.bodyAsText()}")
        }
    }

    return response
}

suspend fun requestGET(
    url: Url,
    body: HashMap<String, String> = hashMapOf(),
    accessToken: String? = null
): HttpResponse {

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

    response.status.let {
        if (it != HttpStatusCode.OK) {
            throw MagisterException(response.status, response.bodyAsText(),
                "Request failed with status code ${it.value} with message ${response.bodyAsText()}")
        }
    }

    return response
}