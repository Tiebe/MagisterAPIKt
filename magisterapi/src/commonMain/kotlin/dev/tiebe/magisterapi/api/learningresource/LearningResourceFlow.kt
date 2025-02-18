@file:Suppress("unused")

package dev.tiebe.magisterapi.api.learningresource

import dev.tiebe.magisterapi.api.json
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.learningresource.LearningResource
import dev.tiebe.magisterapi.utils.format
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

object LearningResourceFlow {
    private const val learningResourcesEndpoint =
        "api/personen/%s/lesmateriaal" // %s = studentId

    suspend fun getLearningResources(tenantUrl: Url, accessToken: String, studentId: Int): List<LearningResource> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                learningResourcesEndpoint.format(studentId)
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val learningResources = jsonData["Items"]?.let { json.decodeFromJsonElement<List<LearningResource>>(it) }
        return learningResources ?: emptyList()
    }

    suspend fun getLearningResourceUrl(tenantUrl: Url, accessToken: String, learningResourceUrl: String): String? {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                "$learningResourceUrl?redirect_type=body"
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        return json["location"]?.jsonPrimitive?.content
    }
}