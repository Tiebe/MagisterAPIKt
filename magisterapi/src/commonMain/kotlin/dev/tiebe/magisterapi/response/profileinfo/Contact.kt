package dev.tiebe.magisterapi.response.profileinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("voorletters")
    val voorletters: String = "",
    @SerialName("tussenvoegsel")
    val tussenvoegsel: String? = null,
    @SerialName("achternaam")
    val achternaam: String = "",
    @SerialName("code")
    val code: String? = null,
    @SerialName("type")
    val type: String = "",
    @SerialName("links")
    val links: Links = Links(),
    @SerialName("roepnaam")
    val roepnaam: String? = null,
    @SerialName("klas")
    val klas: String? = null
) {
    @Serializable
    data class Links(
        @SerialName("self")
        val self: Self = Self()
    ) {
        @Serializable
        data class Self(
            @SerialName("href")
            val href: String = ""
        )
    }
}