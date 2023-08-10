package dev.tiebe.magisterapi.response.assignment


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Docenten(
    @SerialName("Docentcode")
    val docentcode: String,
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val naam: String
): Parcelable