package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Semester {
    @SerialName("Omschrijving")
    var description: String? = null

    @SerialName("Start")
    var start: String? = null

    @SerialName("Einde")
    var end: String? = null

    @SerialName("Id")
    var id: Int? = null

    @SerialName("Naam")
    var name: String? = null

    @SerialName("VolgNummer")
    var index: Int? = null
}