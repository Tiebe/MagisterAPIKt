package dev.tiebe.magisterapi.utils

import dev.tiebe.magisterapi.response.TokenResponse
import io.ktor.utils.io.core.*
import kotlinx.datetime.Clock
import org.kotlincrypto.hash.sha2.SHA256
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.random.Random

object LoginUtility {
    @OptIn(ExperimentalEncodingApi::class)
    fun generateCodeVerifier(): String {
        val codeVerifier = ByteArray(32)
        Random.nextBytes(codeVerifier)
        return Base64.UrlSafe.withPadding(Base64.PaddingOption.ABSENT).encode(codeVerifier)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun generateCodeChallenge(codeVerifier: String): String {
        val bytes: ByteArray =
            codeVerifier.toByteArray()
        return Base64.UrlSafe.withPadding(Base64.PaddingOption.ABSENT).encode(SHA256().digest(bytes))
    }

    fun checkExpired(expiresAt: Long): Boolean {
        return Clock.System.now().epochSeconds > expiresAt
    }

    fun checkExpired(tokenResponse: TokenResponse): Boolean {
        return checkExpired(tokenResponse.expiresAt)
    }
}