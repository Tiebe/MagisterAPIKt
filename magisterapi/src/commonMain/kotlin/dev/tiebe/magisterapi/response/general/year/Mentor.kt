package dev.tiebe.magisterapi.response.general.year

import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class Mentor(
    @SerialName("voorletters")
    val initials: String?,
    @SerialName("tussenvoegsel")
    val preposition: String?,
    @SerialName("achternaam")
    val lastName: String?,
    @SerialName("links")  @IgnoredOnParcel
    val links: JsonObject? = null
): Parcelable