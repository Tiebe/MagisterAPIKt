package dev.tiebe.magisterapi.response.general.year

import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class Group (
    @SerialName("id")
    var id: Int,

    @SerialName("code")
    var code: String,

    @SerialName("omschrijving")
    var description: String,

    @SerialName("links") @IgnoredOnParcel
    var link: JsonObject? = null
): Parcelable