package dev.tiebe.magisterapi.response.messages


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
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
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Links(
            @SerialName("self")
            val self: Link,
            @SerialName("download")
            val downloadLink: Link,
            @SerialName("thumb")
            val thumbnail: Link? = null
        ): Parcelable

        @Serializable @Parcelize
        data class Link(
            @SerialName("href")
            val href: String
        ): Parcelable
    }
}