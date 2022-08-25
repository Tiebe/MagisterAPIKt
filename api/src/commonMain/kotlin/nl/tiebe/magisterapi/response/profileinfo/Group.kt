package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class Group (
    @SerialName("Naam")
    val name: String,

    @SerialName("Privileges")
    val privileges: List<Privilege>,

    @SerialName("Links")
    val links: List<Link?>?,
)