package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class Mentor(
    @SerialName("voorletters")
    val initials: String?,
    @SerialName("tussenvoegsel")
    val preposition: String?,
    @SerialName("achternaam")
    val lastName: String?,
    val links: Link?
)