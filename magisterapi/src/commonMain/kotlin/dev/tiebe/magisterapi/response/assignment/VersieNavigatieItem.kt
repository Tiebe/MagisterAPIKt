package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersieNavigatieItem(
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("Omschrijving")
    val omschrijving: String
) {
    @Serializable
    data class Link(
        @SerialName("Href")
        val href: String,
        @SerialName("Rel")
        val rel: String
    )
}