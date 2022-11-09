package nl.tiebe.magisterapi.response.general.year.absence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Absence(
    @SerialName("Afspraak")
    val appointment: Appointment?,
    @SerialName("AfspraakId")
    val afspraakId: Int,
    @SerialName("Code")
    val code: String,
    @SerialName("Eind")
    val end: String,
    @SerialName("Geoorloofd")
    val justified: Boolean,
    @SerialName("Id")
    val id: Int,
    @SerialName("Lesuur")
    val period: Int,
    @SerialName("Omschrijving")
    val description: String?,
    @SerialName("Start")
    val start: String,
    @SerialName("Verantwoordingtype")
    val type: Int
)
