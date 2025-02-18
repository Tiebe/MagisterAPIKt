package dev.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Classroom(
    @SerialName("Naam")
    val name: String?
)