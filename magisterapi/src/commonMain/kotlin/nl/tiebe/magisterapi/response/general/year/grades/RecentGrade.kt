package nl.tiebe.magisterapi.response.general.year.grades
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonObject


@Serializable
data class RecentGrade(
    @SerialName("behaaldOp")
    val behaaldOp: String?,
    @SerialName("heeftVrijstelling")
    val heeftVrijstelling: Boolean,
    @SerialName("ingevoerdOp")
    val ingevoerdOp: String,
    @SerialName("isVoldoende")
    val isVoldoende: Boolean,
    @SerialName("kolomId")
    val kolomId: Int,
    @SerialName("links")
    val links: JsonObject?,
    @SerialName("moetInhalen")
    val moetInhalen: Boolean,
    @SerialName("omschrijving")
    val omschrijving: String?,
    @SerialName("teltMee")
    val teltMee: Boolean,
    @SerialName("vak")
    val vak: Vak,
    @SerialName("waarde")
    val waarde: String?,
    @SerialName("weegfactor")
    val weegfactor: Double
) {
    @Serializable
    data class Vak(
        @SerialName("code")
        val code: String,
        @SerialName("omschrijving")
        val omschrijving: String
    )
}