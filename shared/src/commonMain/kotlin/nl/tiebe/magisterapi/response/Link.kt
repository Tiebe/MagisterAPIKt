package nl.tiebe.magisterapi.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("self")
class Link {
    @SerialName("href")
    var href: String? = null
}