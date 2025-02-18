package dev.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ProfileInfo (
    @SerialName("UuId")
    val uuId: String,

    @SerialName("Persoon")
    val person: Person,

    @SerialName("Groep")
    val groups: List<Group>,

    @SerialName("Links")
    private val links: List<JsonObject?>,
)