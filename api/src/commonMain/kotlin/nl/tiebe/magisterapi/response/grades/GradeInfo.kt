package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeInfo(
    @SerialName("KolomSoortKolom")
    var columnSortIndex: Int,

    @SerialName("KolomNaam")
    var columnName: String,

    @SerialName("KolomKopnaam")
    var columnHeading: String,

    @SerialName("KolomNiveau")
    var columnLevel: String,

    @SerialName("KolomOmschrijving")
    var columnDescription: String,

    @SerialName("Weging")
    var weight: Double,

    @SerialName("WerkinformatieDatumIngevoerd")
    var workInformationDateEntered: String,

    @SerialName("WerkInformatieOmschrijving")
    var workInformationDescription: String)