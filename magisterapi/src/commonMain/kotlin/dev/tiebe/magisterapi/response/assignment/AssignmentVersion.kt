package dev.tiebe.magisterapi.response.assignment


import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
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
    @SerialName("Links") @IgnoredOnParcel
    val links: JsonObject? = null,
    @SerialName("OpdrachtId")
    val assignmentId: Int,
    @SerialName("Status")
    val status: Int,
    @SerialName("Vak")
    val subject: String,
    @SerialName("VersieNummer")
    val versionIndex: Int
): Parcelable