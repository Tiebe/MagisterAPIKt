package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.response.Link


@Serializable
data class Year(
    @SerialName("id")
    var id: Int,

    @SerialName("studie")
    var study: Study,

    @SerialName("groep")
    var group: Group,

    @SerialName("lesperiode")
    var lessonSemester: LessonSemester,

    @SerialName("profielen")
    var profiles: List<Profile>,

    @SerialName("persoonlijkeMentor")
    var personalMentor: Mentor?,

    @SerialName("begin")
    var start: String,

    @SerialName("einde")
    var end: String,

    @SerialName("isHoofdAanmelding")
    var mainRegistration: Boolean,

    @SerialName("links")
    var link: Link
)