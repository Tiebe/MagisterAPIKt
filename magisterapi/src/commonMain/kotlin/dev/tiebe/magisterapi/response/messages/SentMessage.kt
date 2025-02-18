package dev.tiebe.magisterapi.response.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SentMessage(
    @SerialName("ontvangers")
    val receivers: List<Receiver>,
    @SerialName("kopieOntvangers")
    val ccReceivers: List<Receiver>,
    @SerialName("blindeKopieOntvangers")
    val bccReceivers: List<Receiver>,
    @SerialName("heeftPrioriteit")
    val priority: Boolean,
    @SerialName("inhoud")
    val content: String,
    @SerialName("onderwerp")
    val subject: String,
    @SerialName("verzendOptie")
    val sendingOption: String,
    @SerialName("bijlagen")
    val attachments: List<Attachment>
) {
    @Serializable
    data class Receiver(
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String
    )

    @Serializable
    data class Attachment(
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String
    )


}