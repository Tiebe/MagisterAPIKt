package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Grade {
    @SerialName("CijferId")
    var id: Int? = null

    @SerialName("CijferStr")
    var grade: String? = null

    @SerialName("IsVoldoende")
    var isSufficient: Boolean? = null

    @SerialName("IngevoerdDoor")
    var enteredBy: String? = null

    @SerialName("DatumIngevoerd")
    var dateEntered: String? = null

    @SerialName("CijferPeriode")
    var gradeSemester: GradeSemester? = null

    @SerialName("Vak")
    var subject: Subject? = null

    @SerialName("Inhalen")
    var catchUp: Boolean? = null

    @SerialName("Vrijstelling")
    var exemption: Boolean? = null

    @SerialName("TeltMee")
    var counts: Boolean? = null

    @SerialName("CijferKolom")
    var gradeColumn: GradeColumn? = null

    @SerialName("CijferKolomIdEloOpdracht")
    var gradeInfoIdEloExercise: Int? = null

    @SerialName("Docent")
    var teacher: String? = null

    @SerialName("VakOntheffing")
    var subjectExemption: Boolean? = null

    @SerialName("VakVrijstelling")
    var subjectExemption2: Boolean? = null
    var year: Year? = null

    @Serializable
    class GradeSemester {
        @SerialName("Id")
        var id: Int? = null

        @SerialName("Naam")
        var name: String? = null

        @SerialName("VolgNummer")
        var index: Int? = null
    }
}