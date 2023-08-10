package dev.tiebe.magisterapi.response.messages


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Message(
    @SerialName("afzender")
    val sender: Sender? = null,
    @SerialName("ontvangers")
    val receivers: List<Receiver>? = null,
    @SerialName("beantwoordOp")
    val repliedOn: String? = null,
    @SerialName("doorgestuurdOp")
    val forwardedOn: String? = null,
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
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Sender(
            @SerialName("id")
            val id: Int,
            @SerialName("links")
            val links: Links,
            @SerialName("naam")
            val name: String
        ): Parcelable {
            companion object {
                @Serializable @Parcelize
                data class Links(
                    @SerialName("self")
                    val self: Link? = null
                ): Parcelable
            }
        }

        @Serializable @Parcelize
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
        ): Parcelable

        @Serializable @Parcelize
        data class Links(
            @SerialName("bijlagen")
            val attachments: Link? = null,
            @SerialName("map")
            val folder: Link? = null,
            @SerialName("self")
            val self: Link? = null
        ): Parcelable

        @Serializable @Parcelize
        data class Link(
            @SerialName("href")
            val href: String
        ): Parcelable

    }
}