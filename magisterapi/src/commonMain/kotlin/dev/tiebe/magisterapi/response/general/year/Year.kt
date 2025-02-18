package dev.tiebe.magisterapi.response.general.year

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import dev.tiebe.magisterapi.response.general.year.grades.Study


@Serializable
data class Year(
    @SerialName("id")
    var id: Int,

    @SerialName("studie")
    var study: Study,

    @SerialName("groep")
    var group: Group,

    @SerialName("lesperiode")
    var lessonSemester: Semester,

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
    var link: JsonObject
)