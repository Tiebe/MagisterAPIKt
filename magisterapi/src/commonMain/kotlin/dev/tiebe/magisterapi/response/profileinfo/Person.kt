package dev.tiebe.magisterapi.response.profileinfo

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Person (
    @SerialName("Id")
    val id: Int,

    @SerialName("Roepnaam")
    val firstName: String,

    @SerialName("Tussenvoegsel")
    val preposition: String?,

    @SerialName("Achternaam")
    val lastName: String,

    @SerialName("OfficieleVoornamen")
    val officialFirstNames: String,

    @SerialName("Voorletters")
    val initials: String,

    @SerialName("OfficieleTussenvoegsels")
    val officialPrepositions: String?,

    @SerialName("OfficieleAchternaam")
    val officialLastName: String,

    @SerialName("Geboortedatum")
    val dateOfBirth: String?,

    @SerialName("GeboorteAchternaam")
    val birthLastName: String?,

    @SerialName("GeboortenaamTussenvoegsel")
    val birthNamePreposition: String?,

    @SerialName("GebruikGeboortenaam")
    val useBirthName: Boolean,
): Parcelable