package dev.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Privilege (
    @SerialName("Naam")
    val name: String,

    @SerialName("AccessType")
    val accessType: List<String>,
)