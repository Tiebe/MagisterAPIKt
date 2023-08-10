package dev.tiebe.magisterapi.response.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("aangemaaktOp")
    val createdOn: String,
    @SerialName("contentType")
    val contentType: String,
    @SerialName("gewijzigdOp")
    val changedOn: String,
    @SerialName("grootte")
    val size: Long,
    @SerialName("id")
    val id: Int,
    @SerialName("links")
    val links: Links,
    @SerialName("naam")
    val name: String,
    @SerialName("status")
    val status: String
) {
    companion object {
        @Serializable
        data class Links(
            @SerialName("self")
            val self: Link,
            @SerialName("download")
            val downloadLink: Link,
            @SerialName("thumb")
            val thumbnail: Link? = null
        )

        @Serializable
        data class Link(
            @SerialName("href")
            val href: String
        )
    }
}