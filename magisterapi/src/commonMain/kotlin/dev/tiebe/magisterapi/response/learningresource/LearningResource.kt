package dev.tiebe.magisterapi.response.learningresource


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class LearningResource(
    @SerialName("EAN")
    val eAN: String,
    @SerialName("Eind")
    val end: String?,
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("MateriaalType")
    val resourceType: Int,
    @SerialName("PreviewImageUrl")
    val previewImageUrl: String?,
    @SerialName("Start")
    val start: String?,
    @SerialName("Status")
    val status: Int,
    @SerialName("Titel")
    val title: String,
    @SerialName("Uitgeverij")
    val publisher: String,
    @SerialName("Vak")
    val subject: Subject
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Subject(
            @SerialName("Afkorting")
            val abbreviation: String,
            @SerialName("Id")
            val id: Int,
            @SerialName("LicentieUrl")
            val licenseUrl: String,
            @SerialName("Links")
            val links: List<Link>?,
            @SerialName("Omschrijving")
            val description: String,
            @SerialName("Volgnr")
            val index: Int
        ): Parcelable

        @Serializable @Parcelize
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        ): Parcelable
    }
}