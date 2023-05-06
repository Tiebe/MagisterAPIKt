package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AssignmentVersion(
    @SerialName("Afgesloten")
    val closed: Boolean,
    @SerialName("BeoordeeldOp")
    val gradedOn: String,
    @SerialName("Beoordeling")
    val grade: String,
    @SerialName("Bijlagen")
    val attachments: List<Bijlagen>,
    @SerialName("Docenten")
    val teachers: List<Docenten>,
    @SerialName("Id")
    val id: Int,
    @SerialName("IngeleverdOp")
    val submittedOn: String,
    @SerialName("InleverenVoor")
    val deadline: String,
    @SerialName("LaatsteOpdrachtVersienummer")
    val latestAssignmentVersionNumber: Int,
    @SerialName("Links")
    val links: List<JsonObject>,
    @SerialName("MagInleveren")
    val magInleveren: Boolean,
    @SerialName("Omschrijving")
    val omschrijving: String,
    @SerialName("OpnieuwInleveren")
    val opnieuwInleveren: Boolean,
    @SerialName("StatusLaatsteOpdrachtVersie")
    val statusLaatsteOpdrachtVersie: Int,
    @SerialName("Titel")
    val titel: String,
    @SerialName("Vak")
    val vak: String,
    @SerialName("VersieNavigatieItems")
    val versieNavigatieItems: List<VersieNavigatieItem>
)