package dev.tiebe.magisterapi.response.assignment


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkX(
    @SerialName("Href")
    val href: String,
    @SerialName("Rel")
    val rel: String
)