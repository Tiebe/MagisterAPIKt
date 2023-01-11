@file:Suppress("unused")

package dev.tiebe.magisterapi.api.absence

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.general.year.absence.Absence
import dev.tiebe.magisterapi.utils.format


object AbsenceFlow {
    private const val absenceEndpoint = "api/personen/%s/absenties?van=%s&tot=%s" // %s = account id, %s = start date, %s = end date

    suspend fun getAbsences(tenantUrl: Url, accessToken: String, accountId: Int, start: String, end: String): List<Absence> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                absenceEndpoint.format(accountId, start, end)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val absences = json["Items"]?.let { Json.decodeFromJsonElement<List<Absence>>(it) }
        return absences ?: emptyList()
    }

}
