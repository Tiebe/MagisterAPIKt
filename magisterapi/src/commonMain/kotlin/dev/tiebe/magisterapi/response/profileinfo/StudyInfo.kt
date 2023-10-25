package dev.tiebe.magisterapi.response.profileinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class StudyInfo(
    @SerialName("Studie")
    val studie: String = "",
    @SerialName("Klas")
    val klas: String = "",
    @SerialName("StamNr")
    val stamNr: String = "",
    @SerialName("ExamenNr")
    val examenNr: JsonObject? = null,
    @SerialName("Profielen")
    val profielen: String = ""
)