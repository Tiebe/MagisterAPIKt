package nl.tiebe.magisterapi.response.general.year.agenda
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonObject
import nl.tiebe.magisterapi.response.general.year.Classroom
import nl.tiebe.magisterapi.response.general.year.Subject


@Serializable
data class AgendaItem(
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("Start")
    val start: String,
    @SerialName("Einde")
    val einde: String,
    @SerialName("LesuurVan")
    val fromPeriod: Int?,
    @SerialName("LesuurTotMet")
    val untilPeriod: Int?,
    @SerialName("DuurtHeleDag")
    val lastsAllDay: Boolean,
    @SerialName("Omschrijving")
    val description: String?,
    @SerialName("Lokatie")
    val location: String?,
    @SerialName("Status")
    val status: Int,
    @SerialName("Type")
    val type: Int,
    @SerialName("Subtype")
    val subtype: Int,
    @SerialName("IsOnlineDeelname")
    val isOnline: Boolean,
    @SerialName("WeergaveType")
    val displayType: Int,
    @SerialName("Inhoud")
    val content: String?,
    @SerialName("InfoType")
    val infoType: Int,
    @SerialName("Aantekening")
    val note: String?,
    @SerialName("Afgerond")
    val finished: Boolean,
    @SerialName("HerhaalStatus")
    val repeatingState: Int,
    @SerialName("Herhaling")
    val repeating: JsonObject?,
    @SerialName("Vakken")
    val vakken: List<Subject>,
    @SerialName("Docenten")
    val teachers: List<Teacher>,
    @SerialName("Lokalen")
    val classrooms: List<Classroom>,
    @SerialName("Groepen")
    val groups: JsonObject?,
    @SerialName("OpdrachtId")
    val assignmentId: Int,
    @SerialName("HeeftBijlagen")
    val hasAttachments: Boolean,
    @SerialName("Bijlagen")
    val attachments: JsonObject?
) {
    companion object {
        @Serializable
        data class Link(
            @SerialName("Rel")
            val rel: String,
            @SerialName("Href")
            val href: String
        )

        @Serializable
        data class Repeating(
            @SerialName("BeginDatum")
            val startingDate: String,
            @SerialName("EindDatum")
            val endingDate: String,
            @SerialName("AantalKeer")
            val numberOfTimes: Int,
            @SerialName("Dagelijks")
            val daily: Daily
        )

        @Serializable
        data class Daily(
            @SerialName("Interval")
            val interval: Int
        )

        enum class Type(val type: Int) {
            LESSON(1)
        }
    }
}