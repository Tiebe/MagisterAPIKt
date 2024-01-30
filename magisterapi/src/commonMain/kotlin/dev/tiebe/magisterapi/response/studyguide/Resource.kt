package dev.tiebe.magisterapi.response.studyguide


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Resource(
    @SerialName("BronSoort")
    val resourceType: Int,
    @SerialName("ContentType")
    val contentType: String,
    @SerialName("FileBlobId")
    val fileBlobId: Int,
    @SerialName("GemaaktOp")
    val createdOn: String?,
    @SerialName("GeplaatstDoor")
    val createdBy: JsonObject?,
    @SerialName("GewijzigdOp")
    val modifiedOn: String?,
    @SerialName("Grootte")
    val size: Int,
    @SerialName("Id")
    val id: Int,
    @SerialName("Links")
    val links: List<Link>,
    @SerialName("ModuleSoort")
    val moduleType: Int,
    @SerialName("Naam")
    val name: String,
    @SerialName("ParentId")
    val parentId: Int,
    @SerialName("Privilege")
    val privilege: Int,
    @SerialName("Referentie")
    val reference: Int,
    @SerialName("Type")
    val type: Int,
    @SerialName("UniqueId")
    val uniqueId: String,
    @SerialName("Uri")
    val uri: String?,
    @SerialName("Volgnr")
    val index: Int,
    @SerialName("Zichtbaar")
    val visible: String? = null
) {
    companion object {
        @Serializable
        data class Link(
            @SerialName("Href")
            val href: String,
            @SerialName("Rel")
            val rel: String
        )
    }
}