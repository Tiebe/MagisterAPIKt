package dev.tiebe.magisterapi.api.account

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.benasher44.uuid.uuid4
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import dev.tiebe.magisterapi.api.requestPOST
import dev.tiebe.magisterapi.response.TokenResponse
import dev.tiebe.magisterapi.utils.LoginUtility.generateCodeChallenge
import dev.tiebe.magisterapi.utils.LoginUtility.generateCodeVerifier

object LoginFlow {
    @Serializable @Parcelize
    class AuthURL(val url: String, val codeVerifier: String): Parcelable

    fun createAuthURL(): AuthURL {
        val codeVerifier: String = generateCodeVerifier()
        val codeChallenge: String = generateCodeChallenge(codeVerifier)
        val state: String = uuid4().toString()
        val nonce: String = uuid4().toString()

        return AuthURL("$authorizationEndpoint?client_id=$clientId&redirect_uri=$redirectUri&response_type=${
            responseType.encodeURLParameter()}&scope=${scope.encodeURLParameter()}&state=${state.encodeURLParameter()}&nonce=${nonce.encodeURLParameter()}&code_challenge=${codeChallenge.encodeURLParameter()}&code_challenge_method=${
            codeChallengeMethod.encodeURLParameter()}&prompt=select_account", codeVerifier)
    }

    suspend fun exchangeTokens(code: String, codeVerifier: String): TokenResponse {
        val body: HashMap<String, String> =
            hashMapOf(
                "grant_type" to "authorization_code",
                "code" to code,
                "redirect_uri" to redirectUri,
                "client_id" to clientId,
                "code_verifier" to codeVerifier
            )
        return getToken(body)
    }

    private var mainEndpoint: Url = Url("https://accounts.magister.net")

    fun setMainEndpoint(endpoint: String) {
        mainEndpoint = Url(endpoint)
    }

    fun setAuthorizationEndpoint(endpoint: String) {
        authorizationEndpoint = Url(endpoint)
    }

    fun setTokenEndpoint(endpoint: String) {
        tokenEndpoint = Url(endpoint)
    }

    private var authorizationEndpoint: Url = URLBuilder(mainEndpoint).appendEncodedPathSegments("connect/authorize").build()
    private var tokenEndpoint: Url = URLBuilder(mainEndpoint).appendEncodedPathSegments("connect/token").build()
    const val clientId = "M6LOAPP"
    const val scope = "openid profile email offline_access"
    const val responseType = "id_token code"
    const val redirectUri = "m6loapp://oauth2redirect/"
    const val codeChallengeMethod = "S256"

    suspend fun refreshToken(refreshToken: String): TokenResponse {
        val body: HashMap<String, String> = hashMapOf(
            "grant_type" to "refresh_token",
            "refresh_token" to refreshToken,
            "client_id" to clientId
        )
        return getToken(body)
    }

    private suspend fun getToken(
        body: HashMap<String, String>
    ): TokenResponse {
        val response: HttpResponse = requestPOST(tokenEndpoint, body)
        val tokens: TokenResponse = response.body()

        println(tokens)

        tokens.createdAt = Clock.System.now().epochSeconds

        return tokens
    }

}