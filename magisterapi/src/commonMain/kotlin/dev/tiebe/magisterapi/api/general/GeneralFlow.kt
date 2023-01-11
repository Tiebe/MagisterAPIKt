@file:Suppress("unused")

package dev.tiebe.magisterapi.api.general

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.general.year.Year
import dev.tiebe.magisterapi.utils.format

object GeneralFlow {
    private const val yearsEndpoint =
        "api/leerlingen/%s/aanmeldingen?begin=1970-01-01" // %s = account id

    suspend fun getYears(tenantUrl: String, accessToken: String, accountId: Int): List<Year> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                yearsEndpoint.format(accountId)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val years = json["items"]?.let { Json.decodeFromJsonElement<List<Year>>(it) }
        return years ?: emptyList()
    }


}