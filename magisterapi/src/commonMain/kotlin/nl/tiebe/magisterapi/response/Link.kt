package nl.tiebe.magisterapi.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link (
    @SerialName("self")
    var link: HREF,

    @SerialName("vakken")
    var subjects: HREF? = null,

    @SerialName("perioden")
    var semesters: HREF? = null,

    @SerialName("cijfers")
    var grades: HREF? = null,

    @SerialName("mentoren")
    var mentors: HREF? = null,
)

@Serializable
data class HREF(
    @SerialName("href")
    val href: String
)
