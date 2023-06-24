package dev.tiebe.magisterapi.response.assignment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("BronSoort")
    val attachmentType: Int,
    @SerialName("ContentType")
    val contentType: String,
    @SerialName("Datum")
    val date: String,
    @SerialName("Grootte")
    val size: Int,
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("Naam")
    val naam: String,
    @SerialName("Status")
    val status: Int,
    @SerialName("UniqueId")
    val uniqueId: String,
    @SerialName("Url")
    val url: String?
) {
    @Serializable
    data class Link(
        @SerialName("Href")
        val href: String,
        @SerialName("Rel")
        val rel: String
    )
}