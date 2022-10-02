package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Semester (
    @SerialName("Omschrijving")
    var description: String,

    @SerialName("Start")
    var start: String,

    @SerialName("Einde")
    var end: String,

    @SerialName("Id")
    var id: Int,

    @SerialName("Naam")
    var name: String,

    @SerialName("VolgNummer")
    var index: Int,
)