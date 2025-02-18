package dev.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Mentor(
    @SerialName("voorletters")
    val initials: String?,
    @SerialName("tussenvoegsel")
    val preposition: String?,
    @SerialName("achternaam")
    val lastName: String?,
    val links: JsonObject?
)