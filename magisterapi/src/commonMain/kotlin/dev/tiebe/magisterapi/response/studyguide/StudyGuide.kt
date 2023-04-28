package dev.tiebe.magisterapi.response.studyguide


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyGuide(
    @SerialName("Id")
    val id: Int,
    @SerialName("InLeerlingArchief")
    val archived: Boolean,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("Titel")
    val title: String,
    @SerialName("TotEnMet")
    val expiresOn: String,
    @SerialName("VakCodes")
    val subjectCodes: List<String>,
    @SerialName("Van")
    val startsOn: String
) {
    companion object {
        @Serializable
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        )
    }

}