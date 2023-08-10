package dev.tiebe.magisterapi.response.messages


import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class MessageFolder(
    @SerialName("aantalOngelezen")
    val unreadCount: Int,
    @SerialName("bovenliggendeId")
    val parentId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("links")
    val links: Links,
    @SerialName("naam")
    val name: String
): Parcelable {
    companion object {
        @Serializable @Parcelize
        data class Links(
            @SerialName("self")
            val selfLink: Link,
            @SerialName("parent")
            val parentLink: Link? = null,
            @SerialName("children")
            val subFolderLink: Link? = null,
            @SerialName("berichten")
            val messagesLink: Link
        ): Parcelable

        @Serializable @Parcelize
        data class Link(
            @SerialName("href")
            val href: String
        ): Parcelable
    }
}