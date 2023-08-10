package dev.tiebe.magisterapi.response.studyguide


import com.arkivanov.essenty.parcelable.IgnoredOnParcel
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable @Parcelize
data class StudyGuideContent(
    @SerialName("Id")
    val id: Int,
    @SerialName("InLeerlingArchief")
    val archived: Boolean,
    @SerialName("IsZichtbaar")
    val visible: Boolean,
    @SerialName("Links") @IgnoredOnParcel
    val links: List<JsonObject>? = null,
    @SerialName("Onderdelen")
    val contents: Contents,
    @SerialName("Titel")
    val title: String,
    @SerialName("TotEnMet")
    val expiresOn: String,
    @SerialName("VakCodes")
    val subjectCodes: List<String>,
    @SerialName("Van")
    val startsOn: String
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Contents(
            @SerialName("Items")
            val items: List<Item>,
            @SerialName("Links") @IgnoredOnParcel
            val links: List<JsonObject>? = null,
            @SerialName("TotalCount")
            val totalCount: Int
        ): Parcelable

        @Serializable @Parcelize
        data class Item(
            @SerialName("Bronnen")
            val resources: List<Resource>,
            @SerialName("Id")
            val id: Int,
            @SerialName("IsZichtbaar")
            val visible: Boolean,
            @SerialName("Kleur")
            val color: Int,
            @SerialName("Links")
            val links: List<Link>,
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

        @Serializable @Parcelize
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        ): Parcelable
    }

}