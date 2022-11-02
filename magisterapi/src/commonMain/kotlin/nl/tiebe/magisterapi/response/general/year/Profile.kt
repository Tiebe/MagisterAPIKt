package nl.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class Profile(
    @SerialName("code")
    val code: String,
    @SerialName("links")
    val links: Link,
)