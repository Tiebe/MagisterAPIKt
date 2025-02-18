package dev.tiebe.magisterapi.response.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageData(
    @SerialName("afzender")
    val sender: Sender,
    @SerialName("beantwoordOp")
    val repliedOn: String?,
    @SerialName("blindeKopieOntvangers")
    val bccReceivers: List<Receiver>,
    @SerialName("doorgestuurdOp")
    val forwardedOn: String?,
    @SerialName("heeftBijlagen")
    val hasAttachments: Boolean,
    @SerialName("heeftPrioriteit")
    val hasPriority: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("inhoud")
    val content: String,
    @SerialName("isGelezen")
    val hasBeenRead: Boolean,
    @SerialName("kopieOntvangers")
    val ccReceivers: List<Receiver>,
    @SerialName("links")
    val links: Links,
    @SerialName("mapId")
    val folderId: Int,
    @SerialName("onderwerp")
    val subject: String,
    @SerialName("ontvangers")
    val receivers: List<Receiver>,
    @SerialName("verzondenOp")
    val sentOn: String
) {
    companion object {
        @Serializable
        data class Links(
            @SerialName("bijlagen")
            val attachments: Link? = null,
            @SerialName("map")
            val folder: Link? = null,
            @SerialName("self")
            val self: Link? = null
        )

        @Serializable
        data class Link(
            @SerialName("href")
            val href: String
        )

        @Serializable
        data class Receiver(
            @SerialName("id")
            val id: Int,
            @SerialName("links")
            val links: Links,
            @SerialName("type")
            val type: String,
            @SerialName("mailGroep")
            val mailGroup: String?,
            @SerialName("weergavenaam")
            val name: String
        ) {
            @Serializable
            data class Links(
                @SerialName("self")
                val self: Link? = null
            )
        }

        @Serializable
        data class Sender(
            @SerialName("id")
            val id: Int,
            @SerialName("links")
            val links: Links,
            @SerialName("naam")
            val naam: String
        )


    }
}
