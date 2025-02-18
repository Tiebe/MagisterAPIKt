@file:Suppress("unused")

package dev.tiebe.magisterapi.response.general.year.agenda
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonObject
import dev.tiebe.magisterapi.response.general.year.Classroom
import dev.tiebe.magisterapi.response.general.year.Subject


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
    @SerialName("Opmerking")
    val comment: String?,
    @SerialName("InfoType")
    val infoType: Int,
    @SerialName("Aantekening")
    val note: String?,
    @SerialName("Afgerond")
    val finished: Boolean,
    @SerialName("HerhaalStatus")
    val repeatingState: Int,
    @SerialName("Herhaling")
    val repeating: Repeating?,
    @SerialName("Vakken")
    val subjects: List<Subject>,
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
    val attachments: List<Attachment>?
) {
    fun getInfoType(): InfoType {
        return InfoType.values().find { it.infoType == infoType } ?: InfoType.NONE
    }

    fun getType(): Type {
        return Type.values().find { it.type == type } ?: Type.NONE
    }

    fun getStatus(): Status {
        return Status.values().find { it.status == status } ?: Status.NONE
    }

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
            val endingDate: String? = null,
            @SerialName("AantalKeer")
            val numberOfTimes: Int? = null,
            @SerialName("Dagelijks")
            val daily: Daily? = null,
            @SerialName("Wekelijks")
            val weekly: Weekly? = null

        )

        @Serializable
        data class Daily(
            @SerialName("Interval")
            val interval: Int
        )

        @Serializable
        data class Weekly(
            @SerialName("Interval")
            val interval: Int,
            @SerialName("Dagen")
            val days: Int
        )

        enum class Type(val type: Int) {
            NONE(0), // None
            PERSONAL(1), // Persoonlijk
            GENERAL(2), // Algemeen
            SCHOOL_WIDE(3), // School breed
            INTERNSHIP(4), // Stage
            INTAKE(5), // Intake
            FREE(6), // Roostervrij
            KWT(7), // Kwt
            STANDBY(8), // Standby
            BLOCKED(9), // Blokkade
            OTHER(10), // Overig
            BLOCKED_CLASSROOM(11), // Blokkade lokaal
            BLOCKED_CLASS(12), // Blokkade klas
            CLASS(13), // Les
            STUDY_HOUSE(14), // Studiehuis
            FREE_STUDY(15), // Roostervrije studie
            SCHEDULE(16), // Planning
            MEASURES(101), // Maatregelen
            PRESENTATIONS(102), // Presentaties
            EXAM_SCHEDULE(103) // Examen rooster
        }

        enum class InfoType(val infoType: Int) {
            NONE(0),
            HOMEWORK(1),
            TEST(2),
            EXAM(3),
            WRITTEN_EXAM(4),
            ORAL_EXAM(5),
            INFO(6),
            NOTE(7)
        }

        enum class Status(val status: Int) {
            NONE(0), // Geen status
            SCHEDULED_AUTOMATICALLY(1), // Geroosterd automatisch
            SCHEDULED_MANUALLY(2), // Geroosterd handmatig
            CHANGED(3), // Gewijzigd
            CANCELED_MANUALLY(4), // Vervallen handmatig
            CANCELED_AUTOMATICALLY(5), // Vervallen automatisch
            IN_USE(6), // In gebruik
            FINISHED(7), // Afgesloten
            USED(8), // Ingezet
            MOVED(9), // Verplaatst
            CHANGED_AND_MOVED(10) // Gewijzigd en verplaatst
        }
        @Serializable
        data class Attachment(
            @SerialName("Id")
            val id: Int = 0,
            @SerialName("Naam")
            val naam: String = "",
            @SerialName("ContentType")
            val contentType: String = "",
            @SerialName("Status")
            val status: Int = 0,
            @SerialName("Datum")
            val datum: String = "",
            @SerialName("Grootte")
            val grootte: Int = 0,
            @SerialName("Url")
            val url: String? = null,
            @SerialName("UniqueId")
            val uniqueId: String = "",
            @SerialName("BronSoort")
            val bronSoort: Int = 0,
            @SerialName("Links")
            val links: List<Link> = listOf()
        )
    }
}