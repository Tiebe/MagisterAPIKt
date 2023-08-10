package dev.tiebe.magisterapi.response.profileinfo
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable @Parcelize
data class ContactInfo(
    @SerialName("EloBerichtenDoorsturen")
    val forwardMessages: Boolean,
    @SerialName("EmailAdres")
    val emailAddress: String?,
    @SerialName("Mobiel")
    val phoneNumber: String?
): Parcelable