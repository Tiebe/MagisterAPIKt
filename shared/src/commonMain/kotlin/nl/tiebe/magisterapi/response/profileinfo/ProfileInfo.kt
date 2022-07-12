package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
class ProfileInfo {
    @SerialName("UuId")
    val uuId: String? = null

    @SerialName("Persoon")
    val person: Person? = null

    @SerialName("Groep")
    val groups: List<nl.tiebe.magisterapi.response.profileinfo.Group>? = null

    @SerialName("Links")
    private val links: List<Link?>? = null
}