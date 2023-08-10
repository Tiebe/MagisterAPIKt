package dev.tiebe.magisterapi.response.studyguide


import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class StudyGuideContentItem(
    @SerialName("Bronnen")
    val resources: List<Resource>,
    @SerialName("Id")
    val id: Int,
    @SerialName("IsZichtbaar")
    val visible: Boolean,
    @SerialName("Kleur")
    val color: Int,
    @SerialName("Links") @IgnoredOnParcel
    val links: List<JsonObject>? = null,
    @SerialName("Omschrijving")
    val description: String,
    @SerialName("Titel")
    val title: String,
    @SerialName("TotEnMet")
    val expiresOn: String?,
    @SerialName("Van")
    val startsOn: String?,
    @SerialName("Volgnummer")
    val index: Int
): Parcelable