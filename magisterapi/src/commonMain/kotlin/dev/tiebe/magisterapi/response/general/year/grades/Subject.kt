package dev.tiebe.magisterapi.response.general.year.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject (
    @SerialName("Id")
    var id: Int,

    @SerialName("Afkorting")
    var abbreviation: String,

    @SerialName("Omschrijving")
    var description: String,

    @SerialName("Volgnr")
    var index: Int
)