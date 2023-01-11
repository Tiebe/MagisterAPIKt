package nl.tiebe.magisterapi.response.general.year.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Study (

    @SerialName("id")
    var id: Int,

    @SerialName("code")
    var code: String,

    @SerialName("links")
    var link: JsonObject
)