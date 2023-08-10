package dev.tiebe.magisterapi.response.general.year.grades


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class RecentGrade(
    @SerialName("kolomId")
    val gradeColumnId: Int,
    @SerialName("omschrijving")
    val description: String,
    @SerialName("ingevoerdOp")
    val enteredOn: String,
    @SerialName("vak")
    val subject: Subject,
    @SerialName("waarde")
    val grade: String,
    @SerialName("weegfactor")
    val weight: Double,
    @SerialName("isVoldoende")
    val isSufficient: Boolean,
    @SerialName("teltMee")
    val counts: Boolean,
    @SerialName("moetInhalen")
    val catchUp: Boolean,
    @SerialName("heeftVrijstelling")
    val exemption: Boolean,
    @SerialName("behaaldOp")
    val achievedOn: JsonObject?,
    @SerialName("links")
    val links: JsonObject?
) {
    @Serializable
    data class Subject(
        @SerialName("code")
        val code: String,
        @SerialName("omschrijving")
        val description: String
    )
}