package nl.tiebe.magisterapi.response.general.year.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeColumn (
    @SerialName("Id")
    var id: Int,

    @SerialName("KolomNaam")
    var name: String?,

    @SerialName("KolomNummer")
    var number: String?,

    @SerialName("KolomVolgNummer")
    var index: String,

    @SerialName("KolomKop")
    var heading: String?,

    @SerialName("KolomOmschrijving")
    var description: String?,

    @SerialName("KolomSoort")
    var type: Type,

    @SerialName("IsHerkansingKolom")
    var isCatchUpColumn: Boolean,

    @SerialName("IsDocentKolom")
    var isTeacherColumn: Boolean,

    @SerialName("HeeftOnderliggendeKolommen")
    var HasSubColumns: Boolean,

    @SerialName("IsPTAKolom")
    var isPTAColumn: Boolean,

) {
    @Serializable
    enum class Type(type: Int) {
        @SerialName("0")
        Unknown(0),
        @SerialName("1")
        Grade(1),
        @SerialName("2")
        Average(2),
        @SerialName("3")
        Maximum(3),
        @SerialName("4")
        Formula(4),
        @SerialName("5")
        Minimum(5),
        @SerialName("6")
        Sum(6),
        @SerialName("7")
        Count(7),
        @SerialName("8")
        CEVO(8),
        @SerialName("9")
        Text(9), // Vrije tekst
        @SerialName("10")
        CEVO_CPE(10),
        @SerialName("11")
        CEVO_CIE(11),
        @SerialName("12")
        Weight(12), // weegfactor
        @SerialName("13")
        End(13), // eindcijfer / gemiddelde
        @SerialName("14")
        Deficit(14), // tekortpunten
        @SerialName("15")
        TreeTop(15), // ???
        @SerialName("16")
        SubjectRequirement(16) // vak voorwaarde
    }
}