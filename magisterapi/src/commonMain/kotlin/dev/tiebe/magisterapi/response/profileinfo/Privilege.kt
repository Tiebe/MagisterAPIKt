package dev.tiebe.magisterapi.response.profileinfo

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Privilege (
    @SerialName("Naam")
    val name: String,

    @SerialName("AccessType")
    val accessType: List<String>,
): Parcelable