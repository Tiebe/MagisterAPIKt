package nl.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Group (
    @SerialName("id")
    var id: Int,

    @SerialName("code")
    var code: String,

    @SerialName("omschrijving")
    var description: String,

    @SerialName("links")
    var link: JsonObject
)