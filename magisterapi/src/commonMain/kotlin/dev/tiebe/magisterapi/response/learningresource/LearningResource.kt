package dev.tiebe.magisterapi.response.learningresource


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
) {
    companion object {
        @Serializable
        data class Subject(
            @SerialName("Afkorting")
            val abbreviation: String?,
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
        )

        @Serializable
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        )
    }
}