package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AssignmentVersion(
    @SerialName("BeoordeeldOp")
    val gradedOn: String?,
    @SerialName("Beoordeling")
    val grade: String?,
    @SerialName("DocentOpmerking")
    val teacherNote: String?,
    @SerialName("FeedbackBijlagen")
    val feedbackAttachments: List<Attachment>,
    @SerialName("GestartOp")
    val startedOn: String?,
    @SerialName("Id")
    val id: Int,
    @SerialName("IngeleverdOp")
    val submittedOn: String?,
    @SerialName("InleverenVoor")
    val deadline: String,
    @SerialName("IsTeLaat")
    val isLate: Boolean,
    @SerialName("LeerlingBijlagen")
    val studentAttachments: List<Attachment>,
    @SerialName("LeerlingOpmerking")
    val studentNote: String?,
    @SerialName("Links")
    val links: JsonObject?,
    @SerialName("OpdrachtId")
    val assignmentId: Int,
    @SerialName("Status")
    val status: Int,
    @SerialName("Vak")
    val subject: String,
    @SerialName("VersieNummer")
    val versionIndex: Int
)