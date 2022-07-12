package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link


@Serializable
class Year {
    @SerialName("id")
    var id: Int? = null

    @SerialName("studie")
    var study: Study? = null

    @SerialName("groep")
    var group: Group? = null

    @SerialName("lesperiode")
    var lessonSemester: LessonSemester? = null

    @SerialName("profielen")
    var profiles: List<String>? = null

    @SerialName("persoonlijkeMentor")
    var personalMentor: String? = null

    @SerialName("begin")
    var start: String? = null

    @SerialName("einde")
    var end: String? = null

    @SerialName("isHoofdAanmelding")
    var mainRegistration: Boolean? = null

    @SerialName("links")
    var link: Link? = null
}