package dev.tiebe.magisterapi.response.studyguide


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
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
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        ): Parcelable
    }

}