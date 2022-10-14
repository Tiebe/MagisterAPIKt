package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link

@Serializable
data class LessonSemester (
    @SerialName("code")
    var code: String,

    @SerialName("links")
    var link: Link
)