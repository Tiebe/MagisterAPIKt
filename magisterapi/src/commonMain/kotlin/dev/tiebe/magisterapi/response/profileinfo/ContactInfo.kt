package dev.tiebe.magisterapi.response.profileinfo
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class ContactInfo(
    @SerialName("EloBerichtenDoorsturen")
    val forwardMessages: Boolean,
    @SerialName("EmailAdres")
    val emailAddress: String?,
    @SerialName("Mobiel")
    val phoneNumber: String?
)