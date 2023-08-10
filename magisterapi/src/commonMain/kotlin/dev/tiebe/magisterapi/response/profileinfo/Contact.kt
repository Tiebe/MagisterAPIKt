package dev.tiebe.magisterapi.response.profileinfo


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
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
): Parcelable {
    @Serializable @Parcelize
    data class Links(
        @SerialName("self")
        val self: Self
    ): Parcelable {
        @Serializable @Parcelize
        data class Self(
            @SerialName("href")
            val href: String
        ): Parcelable
    }
}