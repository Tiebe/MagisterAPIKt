package dev.tiebe.magisterapi.response.general.year.grades

import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class Study (

    @SerialName("id")
    var id: Int,

    @SerialName("code")
    var code: String,

    @SerialName("links") @IgnoredOnParcel
    var link: JsonObject? = null
): Parcelable