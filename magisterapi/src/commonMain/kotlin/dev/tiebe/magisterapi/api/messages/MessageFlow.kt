@file:Suppress("unused")

package dev.tiebe.magisterapi.api.messages

import dev.tiebe.magisterapi.api.*
import dev.tiebe.magisterapi.response.messages.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object MessageFlow {
    private const val allFoldersEndpoint = "api/berichten/mappen/alle"
    private const val mainEndpoint = "api/berichten/berichten"

    suspend fun getAllFolders(tenantUrl: Url, accessToken: String): List<MessageFolder> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                allFoldersEndpoint
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val folders = jsonData["items"]?.let { json.decodeFromJsonElement<List<MessageFolder>>(it) }
        return folders ?: emptyList()
    }

    suspend fun getSubFolders(tenantUrl: Url, accessToken: String, subFolderLink: MessageFolder.Companion.Link): List<MessageFolder> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                subFolderLink.href
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val folders = jsonData["items"]?.let { json.decodeFromJsonElement<List<MessageFolder>>(it) }
        return folders ?: emptyList()
    }

    suspend fun getMessages(tenantUrl: Url, accessToken: String, messagesLink: MessageFolder.Companion.Link, amount: Int, skip: Int): List<Message> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                messagesLink.href,
                "?top=$amount&skip=$skip"
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val messages = jsonData["items"]?.let { json.decodeFromJsonElement<List<Message>>(it) }
        return messages ?: emptyList()
    }

    suspend fun getMessageData(tenantUrl: Url, accessToken: String, messageLink: String): MessageData {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                messageLink
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        return json.decodeFromJsonElement(jsonData)
    }

    @Serializable
    internal data class PatchMessageRequest<T>(
        @SerialName("berichten")
        val messages: List<PatchMessage<T>>
    ) {
        companion object {
            @Serializable
            data class PatchMessage<T>(
                @SerialName("berichtId")
                val id: Int,
                @SerialName("operations")
                val operations: List<PatchOperation<T>>
            )

            @Serializable
            data class PatchOperation<T>(
                @SerialName("op")
                val operation: String,
                @SerialName("path")
                val path: String,
                @SerialName("value")
                val value: T
            )
        }
    }

    suspend fun markMessageAsRead(tenantUrl: Url, accessToken: String, messageId: Int, read: Boolean) {
        requestPATCH(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                mainEndpoint
            ).build(), json.encodeToString<PatchMessageRequest<Boolean>>(
                PatchMessageRequest(
                    listOf(
                        PatchMessageRequest.Companion.PatchMessage(
                            messageId,
                            listOf(
                                PatchMessageRequest.Companion.PatchOperation(
                                    "replace",
                                    "/isGelezen",
                                    read,
                                ),
                            ),
                        ),
                    ),
                ),
            ), accessToken
        )
    }
    
    suspend fun getAttachmentList(tenantUrl: Url, accessToken: String, attachmentLink: String): List<Attachment> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                attachmentLink
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()
        val attachments = jsonData["items"]?.let { json.decodeFromJsonElement<List<Attachment>>(it) }
        return attachments ?: emptyList()
    }

    suspend fun downloadAttachment(tenantUrl: Url, accessToken: String, downloadLink: String): ByteReadChannel {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                downloadLink
            ).build(), hashMapOf(), accessToken
        )

        return response.bodyAsChannel()
    }

    suspend fun deleteMessage(tenantUrl: Url, accessToken: String, messageId: Int) = moveMessage(tenantUrl, accessToken, messageId, 3)
    suspend fun restoreMessage(tenantUrl: Url, accessToken: String, messageId: Int) = moveMessage(tenantUrl, accessToken, messageId, 0)

    suspend fun permanentlyDeleteMessage(tenantUrl: Url, accessToken: String, messageId: Int) {
        requestDELETE(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                mainEndpoint
            ).build(),
            listOf(hashMapOf("berichtId" to messageId.toString())), accessToken
        )
    }

    suspend fun moveMessage(tenantUrl: Url, accessToken: String, messageId: Int, folderId: Int) {
        requestPATCH(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                mainEndpoint
            ).build(), json.encodeToString<PatchMessageRequest<Int>>(PatchMessageRequest(
                listOf(
                    PatchMessageRequest.Companion.PatchMessage(
                        messageId,
                        listOf(
                            PatchMessageRequest.Companion.PatchOperation(
                                "replace",
                                "/mapId",
                                folderId
                            )
                        )
                    )
                ))
            ), accessToken
        )
    }

    suspend fun sendMessage(tenantUrl: Url, accessToken: String, subject: String, content: String, to: List<Int>, cc: List<Int>, bcc: List<Int>, priority: Boolean = false, sendingOption: String = "standaard", attachments: List<SentMessage.Attachment> = emptyList()) {
        requestPOST(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                mainEndpoint
            ).build(), json.encodeToString(
                SentMessage(
                    to.map { SentMessage.Receiver(it, "persoon") },
                    cc.map { SentMessage.Receiver(it, "persoon") },
                    bcc.map { SentMessage.Receiver(it, "persoon") },
                    priority,
                    content,
                    subject,
                    sendingOption,
                    attachments
                )
            ), accessToken
        )
    }
}