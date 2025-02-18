package dev.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Group (
    @SerialName("Naam")
    val name: String,

    @SerialName("Privileges")
    val privileges: List<Privilege>,

    @SerialName("Links")
    val links: List<JsonObject?>?,
)