package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
class Study {
    @SerialName("id")
    var id: Int? = null

    @SerialName("code")
    var code: String? = null

    @SerialName("links")
    var link: Link? = null
}