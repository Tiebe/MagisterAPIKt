package dev.tiebe.magisterapi.response.profileinfo

import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class ProfileInfo (
    @SerialName("UuId")
    val uuId: String,

    @SerialName("Persoon")
    val person: Person,

    @SerialName("Groep")
    val groups: List<Group>,

    @SerialName("Links") @IgnoredOnParcel
    private val links: List<JsonObject?>? = null,
): Parcelable