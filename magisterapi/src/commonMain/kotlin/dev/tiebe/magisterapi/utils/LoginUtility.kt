package dev.tiebe.magisterapi.utils

import com.soywiz.krypto.SHA256
import com.soywiz.krypto.SecureRandom
import dev.tiebe.magisterapi.response.TokenResponse
import io.ktor.utils.io.core.*
import kotlinx.datetime.Clock
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object LoginUtility {
    @OptIn(ExperimentalEncodingApi::class)
    fun generateCodeVerifier(): String {
        val codeVerifier = ByteArray(32)
        SecureRandom.nextBytes(codeVerifier)
        return Base64.UrlSafe.withPadding(Base64.PaddingOption.ABSENT).encode(codeVerifier)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun generateCodeChallenge(codeVerifier: String): String {
        val bytes: ByteArray =
            codeVerifier.toByteArray()
        return Base64.UrlSafe.withPadding(Base64.PaddingOption.ABSENT).encode(SHA256.digest(bytes).bytes)
    }

    fun checkExpired(expiresAt: Long): Boolean {
        return Clock.System.now().epochSeconds > expiresAt
    }

    fun checkExpired(tokenResponse: TokenResponse): Boolean {
        return checkExpired(tokenResponse.expiresAt)
    }
}