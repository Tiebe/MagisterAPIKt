package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class ProfileInfo (
    @SerialName("UuId")
    val uuId: String,

    @SerialName("Persoon")
    val person: Person,

    @SerialName("Groep")
    val groups: List<Group>,

    @SerialName("Links")
    private val links: List<Link?>,
)