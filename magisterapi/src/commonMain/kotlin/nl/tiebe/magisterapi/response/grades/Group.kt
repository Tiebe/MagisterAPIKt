package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class Group (
    @SerialName("id")
    var id: Int,

    @SerialName("code")
    var code: String,

    @SerialName("omschrijving")
    var description: String,

    @SerialName("links")
    var link: Link
)