package nl.tiebe.magisterapi.response.profileinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Person {
    @SerialName("Id")
    val id: Int? = null

    @SerialName("Roepnaam")
    val firstName: String? = null

    @SerialName("Tussenvoegsel")
    val prefix: String? = null

    @SerialName("Achternaam")
    val lastName: String? = null

    @SerialName("OfficieleVoornamen")
    val officialFirstNames: String? = null

    @SerialName("Voorletters")
    val initials: String? = null

    @SerialName("OfficieleTussenvoegsels")
    val officialPrefixes: String? = null

    @SerialName("OfficieleAchternaam")
    val officialLastName: String? = null

    @SerialName("Geboortedatum")
    val dateOfBirth: String? = null

    @SerialName("GeboorteAchternaam")
    val birthLastName: String? = null

    @SerialName("GeboortenaamTussenvoegsel")
    val birthNamePrefix: String? = null

    @SerialName("GebruikGeboortenaam")
    val useBirthName: Boolean? = null
}