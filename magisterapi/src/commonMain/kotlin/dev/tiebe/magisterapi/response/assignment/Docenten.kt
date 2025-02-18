package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Docenten(
    @SerialName("Docentcode")
    val docentcode: String,
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val naam: String
)