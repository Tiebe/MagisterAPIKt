package dev.tiebe.magisterapi.response.general.year.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Grade(
    @SerialName("CijferId")
    var id: Int,

    @SerialName("CijferStr")
    var grade: String?,

    @SerialName("IsVoldoende")
    var isSufficient: Boolean,

    @SerialName("IngevoerdDoor")
    var enteredBy: String?,

    @SerialName("DatumIngevoerd")
    var dateEntered: String?,

    @SerialName("CijferPeriode")
    var gradeSemester: GradeSemester?,

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
    var teacher: String?,

    @SerialName("VakOntheffing")
    var subjectExemption: Boolean,

    @SerialName("VakVrijstelling")
    var subjectExemption2: Boolean,
    var yearId: Int = -1


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