package dev.tiebe.magisterapi.response.assignment


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class VersieNavigatieItem(
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("Omschrijving")
    val omschrijving: String
): Parcelable {
    @Serializable @Parcelize
    data class Link(
        @SerialName("Href")
        val href: String,
        @SerialName("Rel")
        val rel: String
    ): Parcelable
}