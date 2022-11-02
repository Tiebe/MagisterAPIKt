package nl.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class Semester (
    @SerialName("code")
    var code: String,

    @SerialName("links")
    var link: Link
)