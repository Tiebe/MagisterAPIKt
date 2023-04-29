package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Assignment(
    @SerialName("Afgesloten")
    val closed: Boolean,
    @SerialName("BeoordeeldOp")
    val gradedOn: String?,
    @SerialName("Beoordeling")
    val grade: String,
    @SerialName("Bijlagen")
    val attachments: List<Bijlagen>,
    @SerialName("Docenten")
    val teachers: List<Docenten>?,
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
) {
    companion object {
        @Serializable
        data class Bijlagen(
            @SerialName("BronSoort")
            val bronSoort: Int,
            @SerialName("ContentType")
            val contentType: String,
            @SerialName("Datum")
            val datum: String,
            @SerialName("Grootte")
            val grootte: Int,
            @SerialName("Id")
            val id: Int,
            @SerialName("Links")
            val links: List<Link>,
            @SerialName("Naam")
            val naam: String,
            @SerialName("Status")
            val status: Int,
            @SerialName("UniqueId")
            val uniqueId: String,
            @SerialName("Url")
            val url: String?
        )

        @Serializable
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        )

        @Serializable
        data class Docenten(
            @SerialName("Docentcode")
            val docentcode: String,
            @SerialName("Id")
            val id: Int,
            @SerialName("Naam")
            val naam: String
        )

        @Serializable
        data class VersieNavigatieItem(
            @SerialName("Id")
            val id: Int,
            @SerialName("Links")
            val links: List<Link>,
            @SerialName("Omschrijving")
            val description: String
        )
    }
}