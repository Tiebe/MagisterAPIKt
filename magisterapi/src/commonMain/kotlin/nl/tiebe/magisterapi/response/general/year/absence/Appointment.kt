package nl.tiebe.magisterapi.response.general.year.absence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import nl.tiebe.magisterapi.response.general.year.Classroom
import nl.tiebe.magisterapi.response.general.year.Subject

@Serializable
data class Appointment(
    @SerialName("Aantekening")
    val note: String?,
    @SerialName("Afgerond")
    val finished: Boolean,
    @SerialName("Bijlagen")
    val attachments: String?,
    @SerialName("Docenten")
    val teachers: String?,
    @SerialName("DuurtHeleDag")
    val lastsAllDay: Boolean,
    @SerialName("Einde")
    val end: String,
    @SerialName("Groepen")
    val groups: String?,
    @SerialName("HeeftBijlagen")
    val hasAttachments: Boolean,
    @SerialName("HerhaalStatus")
    val repeatState: Int,
    @SerialName("Herhaling")
    val repeating: String?,
    @SerialName("Id")
    val id: Int,
    @SerialName("InfoType")
    val infoType: Int,
    @SerialName("Inhoud")
    val content: String?,
    @SerialName("IsOnlineDeelname")
    val isOnline: Boolean,
    @SerialName("LesuurTotMet")
    val periodUntil: Int,
    @SerialName("LesuurVan")
    val periodFrom: Int,
    @SerialName("Links")
    val links: JsonObject?,
    @SerialName("Lokalen")
    val classrooms: List<Classroom>,
    @SerialName("Lokatie")
    val location: String?,
    @SerialName("Omschrijving")
    val description: String?,
    @SerialName("OpdrachtId")
    val assignmentId: Int,
    @SerialName("Start")
    val start: String,
    @SerialName("Status")
    val status: Int,
    @SerialName("Subtype")
    val subtype: Int,
    @SerialName("Type")
    val type: Int,
    @SerialName("Vakken")
    val subjects: List<Subject>?,
    @SerialName("WeergaveType")
    val displayType: Int
)