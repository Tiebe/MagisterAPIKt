package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Privilege {
    @SerialName("Naam")
    val name: String? = null

    @SerialName("AccessType")
    val accessType: List<String>? = null
}