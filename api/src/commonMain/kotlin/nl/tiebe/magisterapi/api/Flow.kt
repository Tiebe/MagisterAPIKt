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
import kotlin.jvm.JvmStatic

abstract class Flow(var account: Account) {

    companion object {
        val mainEndpoint: Url = Url("https://nuovo.magister.net/")
        private val client = HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }

        @JvmStatic
        protected suspend fun requestPOST(
            url: Url,
            body: HashMap<String, String>
        ): HttpResponse {


            val response = client.submitForm(url.toString(),
                formParameters = Parameters.build {
                    for ((key, value) in body) {
                        append(key, value)
                    }
                }
            )

            response.status.let {
                if (it != HttpStatusCode.OK) {
                    throw MagisterException("Request failed with status code ${it.value} with message ${response.bodyAsText()}}")
                }
            }

            return response
        }

        @JvmStatic
        protected suspend fun requestGET(
            url: Url,
            body: HashMap<String, String>,
            accessToken: String?
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
                    throw MagisterException("Request failed with status code ${it.value} with message ${response.bodyAsText()}}")
                }
            }
            println(response.bodyAsText())

            return response
        }
    }

}