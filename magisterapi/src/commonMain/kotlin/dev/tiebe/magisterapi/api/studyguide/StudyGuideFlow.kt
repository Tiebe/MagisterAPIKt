@file:Suppress("DuplicatedCode", "unused", "MemberVisibilityCanBePrivate")

package dev.tiebe.magisterapi.api.studyguide

import dev.tiebe.magisterapi.api.json
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.studyguide.StudyGuide
import dev.tiebe.magisterapi.response.studyguide.StudyGuideContent
import dev.tiebe.magisterapi.response.studyguide.StudyGuideContentItem
import dev.tiebe.magisterapi.utils.format
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object StudyGuideFlow {
    private const val studyGuideEndpoint = "api/leerlingen/%s/studiewijzers?peildatum=%s" // %s = studentId, date
    private const val projectEndpoint = "api/leerlingen/%s/projecten?peildatum=%s" // %s = studentId, date

    suspend fun getFullStudyGuideList(tenantUrl: Url, accessToken: String, studentId: Int, date: String): List<StudyGuide> {
        val studyGuideList = getStudyGuideList(tenantUrl, accessToken, studentId, date)
        val projectList = getProjectList(tenantUrl, accessToken, studentId, date)
        return (studyGuideList + projectList).sortedBy { it.title }
    }

    suspend fun getStudyGuideList(tenantUrl: Url, accessToken: String, studentId: Int, date: String): List<StudyGuide> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                studyGuideEndpoint.format(studentId, date)
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val studyGuide = jsonData["Items"]?.let { json.decodeFromJsonElement<List<StudyGuide>>(it) }
        return studyGuide ?: emptyList()
    }

    suspend fun getProjectList(tenantUrl: Url, accessToken: String, studentId: Int, date: String): List<StudyGuide> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                projectEndpoint.format(studentId, date)
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val studyGuide = jsonData["Items"]?.let { json.decodeFromJsonElement<List<StudyGuide>>(it) }
        return studyGuide ?: emptyList()
    }

    suspend fun getStudyGuideContent(tenantUrl: Url, accessToken: String, studyGuideLink: String): StudyGuideContent {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                studyGuideLink
            ).build(), hashMapOf(), accessToken
        )

        return response.body()
    }

    suspend fun getStudyGuideContentItem(tenantUrl: Url, accessToken: String, itemLink: String, useFolders: Boolean = false): StudyGuideContentItem {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                itemLink.run { if (useFolders) "$this?gebruikMappenStructuur=true" else this }
            ).build(), hashMapOf(), accessToken
        )

        return response.body()
    }

    suspend fun downloadAttachment(tenantUrl: Url, accessToken: String, downloadLink: String): ByteReadChannel {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                downloadLink
            ).build(), hashMapOf(), accessToken
        )

        return response.bodyAsChannel()
    }


}