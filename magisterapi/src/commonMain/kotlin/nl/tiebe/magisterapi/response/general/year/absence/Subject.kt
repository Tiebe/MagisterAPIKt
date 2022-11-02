package nl.tiebe.magisterapi.response.general.year.absence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val name: String?
)
