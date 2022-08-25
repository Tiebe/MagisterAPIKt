package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeColumn (
    @SerialName("Id")
    var id: Int,

    @SerialName("KolomNaam")
    var name: String,

    @SerialName("KolomNummer")
    var number: String,

    @SerialName("KolomVolgNummer")
    var index: String,

    @SerialName("KolomKop")
    var heading: String,

    @SerialName("KolomOmschrijving")
    var description: String,

    @SerialName("KolomSoort")
    var type: Int,

    @SerialName("IsHerkansingKolom")
    var isCatchUpColumn: Boolean,
)