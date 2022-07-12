package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GradeColumn {
    @SerialName("Id")
    var id: Int? = null

    @SerialName("KolomNaam")
    var name: String? = null

    @SerialName("KolomNummer")
    var number: String? = null

    @SerialName("KolomVolgNummer")
    var index: String? = null

    @SerialName("KolomKop")
    var heading: String? = null

    @SerialName("KolomOmschrijving")
    var description: String? = null

    @SerialName("KolomSoort")
    var type: Int? = null

    @SerialName("IsHerkansingKolom")
    var isCatchUpColumn: Boolean? = null
}