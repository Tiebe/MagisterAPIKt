package nl.tiebe.magisterapi.api.agenda

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import nl.tiebe.magisterapi.api.requestGET
import nl.tiebe.magisterapi.response.general.year.agenda.AgendaItem
import nl.tiebe.magisterapi.utils.format

object AgendaFlow {
    private const val agendaEndpoint = "api/personen/%s/afspraken?status=1&van=%s&tot=%s" // %s = account id, %s = start date, %s = end date

    suspend fun getAgenda(tenantUrl: Url, accessToken: String, accountId: Int, start: String, end: String): List<AgendaItem> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                agendaEndpoint.format(accountId, start, end)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val agenda = json["Items"]?.let { Json.decodeFromJsonElement<List<AgendaItem>>(it) }
        return agenda ?: emptyList()
    }


}

