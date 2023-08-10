package dev.tiebe.magisterapi.response.profileinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("id")
    val id: Int,
    @SerialName("voorletters")
    val voorletters: String,
    @SerialName("roepnaam")
    val roepnaam: String? = null,
    @SerialName("tussenvoegsel")
    val tussenvoegsel: String? = null,
    @SerialName("achternaam")
    val lastName: String,
    @SerialName("code")
    val teacherAbbreviation: String? = null,
    @SerialName("type")
    val type: String,
    @SerialName("links")
    val links: Links
) {
    @Serializable
    data class Links(
        @SerialName("self")
        val self: Self
    ) {
        @Serializable
        data class Self(
            @SerialName("href")
            val href: String
        )
    }
}