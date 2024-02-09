@file:Suppress("unused")

package dev.tiebe.magisterapi.api.agenda

import dev.tiebe.magisterapi.api.json
import io.ktor.client.call.*
import io.ktor.http.*
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.general.year.agenda.AgendaItem
import dev.tiebe.magisterapi.utils.format
import kotlinx.serialization.json.*

object AgendaFlow {
    private const val agendaEndpoint = "api/personen/%s/afspraken?status=%s&van=%s&tot=%s" // %s = account id, %s = status, %s = start date, %s = end date

    suspend fun getAgenda(tenantUrl: String, accessToken: String, accountId: Int, start: String, end: String, status: AgendaItem.Companion.Status): List<AgendaItem> =
        getAgenda(tenantUrl, accessToken, accountId, start, end, status.status)

    suspend fun getAgenda(tenantUrl: String, accessToken: String, accountId: Int, start: String, end: String, status: Int = 0): List<AgendaItem> =
        getAgenda(Url(tenantUrl), accessToken, accountId, start, end, status)

    suspend fun getAgenda(tenantUrl: Url, accessToken: String, accountId: Int, start: String, end: String, status: AgendaItem.Companion.Status): List<AgendaItem> =
        getAgenda(tenantUrl, accessToken, accountId, start, end, status.status)

    suspend fun getAgenda(tenantUrl: Url, accessToken: String, accountId: Int, start: String, end: String, status: Int = 0): List<AgendaItem> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                agendaEndpoint.format(accountId, status, start, end)
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()

        val agenda = jsonData["Items"]?.let { json.decodeFromJsonElement<List<AgendaItem>>(it) }
        return agenda ?: emptyList()
    }

    suspend fun getAgendaItem(tenantUrl: String, accessToken: String, accountId: Int, itemId: Int): AgendaItem {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                "api/personen/%s/afspraken/%s".format(accountId, itemId)
            ).build(), hashMapOf(), accessToken
        )

        return response.body()
    }


}

