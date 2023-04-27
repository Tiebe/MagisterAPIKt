@file:Suppress("unused")

package dev.tiebe.magisterapi.api.messages

import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.api.requestPATCH
import dev.tiebe.magisterapi.response.messages.Message
import dev.tiebe.magisterapi.response.messages.MessageData
import dev.tiebe.magisterapi.response.messages.MessageFolder
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.http.URLBuilder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object MessageFlow {
    private const val allFoldersEndpoint = "api/berichten/mappen/alle"
    private const val patchEndpoint = "api/berichten/berichten"

    suspend fun getAllFolders(tenantUrl: Url, accessToken: String): List<MessageFolder> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                allFoldersEndpoint
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val folders = json["items"]?.let { Json.decodeFromJsonElement<List<MessageFolder>>(it) }
        return folders ?: emptyList()
    }

    suspend fun getSubFolders(tenantUrl: Url, accessToken: String, subFolderLink: MessageFolder.Companion.Link): List<MessageFolder> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                subFolderLink.href
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val folders = json["items"]?.let { Json.decodeFromJsonElement<List<MessageFolder>>(it) }
        return folders ?: emptyList()
    }

    suspend fun getMessages(tenantUrl: Url, accessToken: String, messagesLink: MessageFolder.Companion.Link, amount: Int, skip: Int): List<Message> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                messagesLink.href,
                "?top=$amount&skip=$skip"
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val messages = json["items"]?.let { Json.decodeFromJsonElement<List<Message>>(it) }
        return messages ?: emptyList()
    }

    suspend fun getMessageData(tenantUrl: Url, accessToken: String, messageLink: String): MessageData {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                messageLink
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        return Json.decodeFromJsonElement(json)
    }

    @Serializable
    internal data class PatchMessageRequest(
        @SerialName("berichten")
        val messages: List<PatchMessage>
    ) {
        companion object {
            @Serializable
            data class PatchMessage(
                @SerialName("berichtId")
                val id: Int,
                @SerialName("operations")
                val operations: List<PatchOperation>
            )

            @Serializable
            data class PatchOperation(
                @SerialName("op")
                val operation: String,
                @SerialName("path")
                val path: String,
                @SerialName("value")
                val value: Boolean
            )
        }
    }

    suspend fun markMessageAsRead(tenantUrl: Url, accessToken: String, messageId: Int, read: Boolean) {
        requestPATCH(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                patchEndpoint
            ).build(), PatchMessageRequest(
                listOf(
                    PatchMessageRequest.Companion.PatchMessage(
                        messageId,
                        listOf(
                            PatchMessageRequest.Companion.PatchOperation(
                                "replace",
                                "/isGelezen",
                                read
                            )
                        )
                    )
                )
            ), accessToken
        )


    }
}