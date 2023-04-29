@file:Suppress("unused")

package dev.tiebe.magisterapi.api.assignment

import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.assignment.Assignment
import dev.tiebe.magisterapi.utils.format
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object AssignmentFlow {
    private const val assignmentsUrl =
        "api/personen/%s/opdrachten?skip=%s&top=%s&startdatum=%s&einddatum=%s" // %s = studentId, skip, top, startdate, enddate


    suspend fun getAssignments(
        tenantUrl: Url,
        accessToken: String,
        studentId: Int,
        skip: Int,
        top: Int,
        startDate: String,
        endDate: String
    ): List<Assignment> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                assignmentsUrl.format(studentId, skip, top, startDate, endDate)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val assignments = json["Items"]?.let { Json.decodeFromJsonElement<List<Assignment>>(it) }
        return assignments ?: emptyList()
    }

    suspend fun Assignment.getExtraAssignmentInfo(
        tenantUrl: Url,
        accessToken: String
    ): Assignment {
        return getFullAssignment(tenantUrl, accessToken, this.links.first { it.rel == "Self" }.href)
    }

    suspend fun getFullAssignment(
        tenantUrl: Url,
        accessToken: String,
        assignmentLink: String
    ): Assignment {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                assignmentLink
            ).build(), hashMapOf(), accessToken
        )

        return response.body()
    }

}