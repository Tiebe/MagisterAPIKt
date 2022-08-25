package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link
import nl.tiebe.magisterapi.response.HREF

@Serializable
data class Grade(
    @SerialName("CijferId")
    var id: Int,

    @SerialName("CijferStr")
    var grade: String,

    @SerialName("IsVoldoende")
    var isSufficient: Boolean,

    @SerialName("IngevoerdDoor")
    var enteredBy: String,

    @SerialName("DatumIngevoerd")
    var dateEntered: String,

    @SerialName("CijferPeriode")
    var gradeSemester: GradeSemester,

    @SerialName("Vak")
    var subject: Subject,

    @SerialName("Inhalen")
    var catchUp: Boolean,

    @SerialName("Vrijstelling")
    var exemption: Boolean,

    @SerialName("TeltMee")
    var counts: Boolean,

    @SerialName("CijferKolom")
    var gradeColumn: GradeColumn,

    @SerialName("CijferKolomIdEloOpdracht")
    var gradeInfoIdEloExercise: Int,

    @SerialName("Docent")
    var teacher: String,

    @SerialName("VakOntheffing")
    var subjectExemption: Boolean,

    @SerialName("VakVrijstelling")
    var subjectExemption2: Boolean,
    var year: Year = Year(0, Study(0, "", Link(HREF(""))), Group(0, "", "", Link(HREF(""))), LessonSemester("", Link(HREF(""))), listOf(), "", "", "", false, Link(HREF("")))


) {
    @Serializable
    data class GradeSemester(
        @SerialName("Id")
        var id: Int,

        @SerialName("Naam")
        var name: String,

        @SerialName("VolgNummer")
        var index: Int
    )
}