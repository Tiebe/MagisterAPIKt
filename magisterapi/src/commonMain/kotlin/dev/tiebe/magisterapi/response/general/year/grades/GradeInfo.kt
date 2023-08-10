package dev.tiebe.magisterapi.response.general.year.grades

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class GradeInfo(
    @SerialName("KolomSoortKolom")
    var columnSortIndex: Int,

    @SerialName("KolomNaam")
    var columnName: String?,

    @SerialName("KolomKopnaam")
    var columnHeading: String?,

    @SerialName("KolomNiveau")
    var columnLevel: String?,

    @SerialName("KolomOmschrijving")
    var columnDescription: String?,

    @SerialName("Weging")
    var weight: Double,

    @SerialName("WerkinformatieDatumIngevoerd")
    var workInformationDateEntered: String?,

    @SerialName("WerkInformatieOmschrijving")
    var workInformationDescription: String?
): Parcelable