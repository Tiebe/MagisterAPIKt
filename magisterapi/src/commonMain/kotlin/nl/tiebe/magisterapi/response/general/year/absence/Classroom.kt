package nl.tiebe.magisterapi.response.general.year.absence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Classroom(
    @SerialName("Naam")
    val name: String?
)