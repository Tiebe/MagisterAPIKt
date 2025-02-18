package dev.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val name: String?
)
