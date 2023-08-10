package dev.tiebe.magisterapi.response.general.year.grades

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class GradeSemester (
    @SerialName("Omschrijving")
    var description: String,

    @SerialName("Start")
    var start: String,

    @SerialName("Einde")
    var end: String,

    @SerialName("Id")
    var id: Int,

    @SerialName("Naam")
    var name: String,

    @SerialName("VolgNummer")
    var index: Int,
): Parcelable