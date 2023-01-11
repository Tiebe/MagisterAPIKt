package nl.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Profile(
    @SerialName("code")
    val code: String,
    @SerialName("links")
    val links: JsonObject,
)