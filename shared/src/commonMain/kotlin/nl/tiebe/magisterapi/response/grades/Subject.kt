package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Subject {
    @SerialName("Id")
    var id: Int? = null

    @SerialName("Afkorting")
    var abbreviation: String? = null

    @SerialName("Omschrijving")
    var description: String? = null

    @SerialName("Volgnr")
    var index: Int? = null
}