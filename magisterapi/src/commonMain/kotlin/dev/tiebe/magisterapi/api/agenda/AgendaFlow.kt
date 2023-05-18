@file:Suppress("unused")

package dev.tiebe.magisterapi.api.agenda

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.general.year.agenda.AgendaItem
import dev.tiebe.magisterapi.utils.format

object AgendaFlow {
    private const val agendaEndpoint = "api/personen/%s/afspraken?%svan=%s&tot=%s" // %s = account id, %s = status, %s = start date, %s = end date

    suspend fun getAgenda(tenantUrl: Url, accessToken: String, accountId: Int, start: String, end: String, status: Int? = null): List<AgendaItem> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                agendaEndpoint.format(accountId, if (status != null) "status=$status&" else "", start, end)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()

        val agenda = json["Items"]?.let { Json.decodeFromJsonElement<List<AgendaItem>>(it) }
        return agenda ?: emptyList()
    }


}

