package dev.tiebe.magisterapi.response.profileinfo

import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class Group (
    @SerialName("Naam")
    val name: String,

    @SerialName("Privileges")
    val privileges: List<Privilege>,

    @SerialName("Links") @IgnoredOnParcel
    val links: List<JsonObject?>? = null,
): Parcelable