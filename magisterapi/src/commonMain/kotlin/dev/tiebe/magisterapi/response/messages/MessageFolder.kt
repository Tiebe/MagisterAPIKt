package dev.tiebe.magisterapi.response.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
) {
    companion object {
        @Serializable
        data class Links(
            @SerialName("self")
            val selfLink: Link,
            @SerialName("parent")
            val parentLink: Link? = null,
            @SerialName("children")
            val subFolderLink: Link? = null,
            @SerialName("berichten")
            val messagesLink: Link
        )

        @Serializable
        data class Link(
            @SerialName("href")
            val href: String
        )
    }
}