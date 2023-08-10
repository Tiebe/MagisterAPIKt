package dev.tiebe.magisterapi.response.assignment


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Assignment(
    @SerialName("Afgesloten")
    val closed: Boolean,
    @SerialName("BeoordeeldOp")
    val gradedOn: String?,
    @SerialName("Beoordeling")
    val grade: String,
    @SerialName("Bijlagen")
    val attachments: List<Attachment>,
    @SerialName("Docenten")
    val teachers: List<Teacher>?,
    @SerialName("Id")
    val id: Int,
    @SerialName("IngeleverdOp")
    val submittedOn: String?,
    @SerialName("InleverenVoor")
    val deadline: String,
    @SerialName("LaatsteOpdrachtVersienummer")
    val latestAssignmentVersionNumber: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("MagInleveren")
    val submitAllowed: Boolean,
    @SerialName("Omschrijving")
    val description: String,
    @SerialName("OpnieuwInleveren")
    val submitAgain: Boolean,
    @SerialName("StatusLaatsteOpdrachtVersie")
    val latestAssignmentStatus: Int,
    @SerialName("Titel")
    val title: String,
    @SerialName("Vak")
    val subject: String,
    @SerialName("VersieNavigatieItems")
    val navigationItemsVersion: List<VersieNavigatieItem>,

    val hasExtraInfo: Boolean = false
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Attachment(
            @SerialName("BronSoort")
            val resourceType: Int,
            @SerialName("ContentType")
            val contentType: String,
            @SerialName("Datum")
            val datum: String,
            @SerialName("Grootte")
            val size: Int,
            @SerialName("Id")
            val id: Int,
            @SerialName("Links")
            val links: List<Link>,
            @SerialName("Naam")
            val name: String,
            @SerialName("Status")
            val status: Int,
            @SerialName("UniqueId")
            val uniqueId: String,
            @SerialName("Url")
            val url: String?
        ): Parcelable

        @Serializable @Parcelize
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        ): Parcelable

        @Serializable @Parcelize
        data class Teacher(
            @SerialName("Docentcode")
            val teacherCode: String,
            @SerialName("Id")
            val id: Int,
            @SerialName("Naam")
            val name: String
        ): Parcelable

        @Serializable @Parcelize
        data class VersieNavigatieItem(
            @SerialName("Id")
            val id: Int,
            @SerialName("Links")
            val links: List<Link>,
            @SerialName("Omschrijving")
            val description: String
        ): Parcelable
    }
}