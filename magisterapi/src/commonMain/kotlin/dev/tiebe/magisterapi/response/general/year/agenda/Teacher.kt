package dev.tiebe.magisterapi.response.general.year.agenda

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Teacher(
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val name: String,
    @SerialName("Docentcode")
    val teacherCode: String
)