package dev.tiebe.magisterapi.response.general.year

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Subject(
    @SerialName("Id")
    val id: Int,
    @SerialName("Naam")
    val name: String?
): Parcelable
