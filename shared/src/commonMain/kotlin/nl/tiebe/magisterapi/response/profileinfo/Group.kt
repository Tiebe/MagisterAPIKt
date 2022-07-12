package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
class Group {
    @SerialName("Naam")
    val name: String? = null

    @SerialName("Privileges")
    val privileges: List<Privilege>? = null

    @SerialName("Links")
    val links: List<Link?>? = null
}