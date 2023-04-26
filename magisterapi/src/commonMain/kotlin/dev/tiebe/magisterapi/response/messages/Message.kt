package dev.tiebe.magisterapi.response.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("afzender")
    val sender: Sender,
    @SerialName("beantwoordOp")
    val repliedOn: String?,
    @SerialName("doorgestuurdOp")
    val forwardedOn: String?,
    @SerialName("heeftBijlagen")
    val hasAttachments: Boolean,
    @SerialName("heeftPrioriteit")
    val hasPriority: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("isGelezen")
    val hasBeenRead: Boolean,
    @SerialName("links")
    val links: Links,
    @SerialName("mapId")
    val folderId: Int,
    @SerialName("onderwerp")
    val subject: String,
    @SerialName("verzondenOp")
    val sentOn: String
) {
    companion object {
        @Serializable
        data class Sender(
            @SerialName("id")
            val id: Int,
            @SerialName("links")
            val links: Links,
            @SerialName("naam")
            val name: String
        ) {
            companion object {
                @Serializable
                data class Links(
                    @SerialName("self")
                    val self: Link
                )
            }
        }

        @Serializable
        data class Links(
            @SerialName("bijlagen")
            val attachments: Link? = null,
            @SerialName("map")
            val folder: Link,
            @SerialName("self")
            val self: Link
        )

        @Serializable
        data class Link(
            @SerialName("href")
            val href: String
        )

    }
}