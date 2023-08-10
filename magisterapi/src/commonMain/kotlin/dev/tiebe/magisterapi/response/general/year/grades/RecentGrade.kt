package dev.tiebe.magisterapi.response.general.year.grades


import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
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
    @SerialName("behaaldOp") @IgnoredOnParcel
    val achievedOn: JsonObject? = null,
    @SerialName("links") @IgnoredOnParcel
    val links: JsonObject? = null
): Parcelable {
    @Serializable @Parcelize
    data class Subject(
        @SerialName("code")
        val code: String,
        @SerialName("omschrijving")
        val description: String
    ): Parcelable
}