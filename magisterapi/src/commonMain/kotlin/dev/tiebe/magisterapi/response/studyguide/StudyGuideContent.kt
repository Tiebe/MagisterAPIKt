package dev.tiebe.magisterapi.response.studyguide


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class StudyGuideContent(
    @SerialName("Id")
    val id: Int,
    @SerialName("InLeerlingArchief")
    val archived: Boolean,
    @SerialName("IsZichtbaar")
    val visible: Boolean,
    @SerialName("Links")
    val links: List<JsonObject>?,
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
) {
    companion object {
        @Serializable
        data class Contents(
            @SerialName("Items")
            val items: List<Item>,
            @SerialName("Links")
            val links: List<JsonObject>,
            @SerialName("TotalCount")
            val totalCount: Int
        )

        @Serializable
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
        )

        @Serializable
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        )
    }

}