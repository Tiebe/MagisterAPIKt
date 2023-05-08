package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeerlingBijlagen(
    @SerialName("BronSoort")
    val bronSoort: Int,
    @SerialName("ContentType")
    val contentType: String,
    @SerialName("Datum")
    val datum: String,
    @SerialName("Grootte")
    val grootte: Int,
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<LinkX>,
    @SerialName("Naam")
    val naam: String,
    @SerialName("Status")
    val status: Int,
    @SerialName("UniqueId")
    val uniqueId: String,
    @SerialName("Url")
    val url: String?
)