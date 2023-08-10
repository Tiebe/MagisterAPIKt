package dev.tiebe.magisterapi.response.general.year.grades

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Subject (
    @SerialName("Id")
    var id: Int,

    @SerialName("Afkorting")
    var abbreviation: String,

    @SerialName("Omschrijving")
    var description: String,

    @SerialName("Volgnr")
    var index: Int
): Parcelable