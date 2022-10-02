package nl.tiebe.magisterapi.utils

import com.soywiz.krypto.SHA256
import com.soywiz.krypto.SecureRandom
import io.ktor.utils.io.core.*
import io.matthewnelson.component.base64.Base64
import io.matthewnelson.component.base64.encodeBase64
import kotlinx.datetime.Clock
import nl.tiebe.magisterapi.response.TokenResponse

object LoginUtility {
    fun generateCodeVerifier(): String {
        val codeVerifier = ByteArray(32)
        SecureRandom.nextBytes(codeVerifier)
        return codeVerifier.encodeBase64(base64 = Base64.UrlSafe(pad = false))
    }

    fun generateCodeChallenge(codeVerifier: String): String {
        val bytes: ByteArray =
            codeVerifier.toByteArray()

        return SHA256.digest(bytes).bytes.encodeBase64(base64 = Base64.UrlSafe(pad = false))
    }

    fun checkExpired(expiresAt: Long): Boolean {
        return Clock.System.now().epochSeconds > expiresAt
    }

    fun checkExpired(tokenResponse: TokenResponse): Boolean {
        return checkExpired(tokenResponse.expiresAt)
    }
}