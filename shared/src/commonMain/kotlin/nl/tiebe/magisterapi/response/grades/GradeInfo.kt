package nl.tiebe.magisterapi.response.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GradeInfo {
    @SerialName("KolomSoortKolom")
    var columnSortIndex: Int? = null

    @SerialName("KolomNaam")
    var columnName: String? = null

    @SerialName("KolomKopnaam")
    var columnHeading: String? = null

    @SerialName("KolomNiveau")
    var columnLevel: String? = null

    @SerialName("KolomOmschrijving")
    var columnDescription: String? = null

    @SerialName("Weging")
    var weight: Double? = null

    @SerialName("WerkinformatieDatumIngevoerd")
    var workInformationDateEntered: String? = null

    @SerialName("WerkInformatieOmschrijving")
    var workInformationDescription: String? = null
}