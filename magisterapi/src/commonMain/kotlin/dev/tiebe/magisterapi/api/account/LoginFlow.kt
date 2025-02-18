package dev.tiebe.magisterapi.api.account

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import dev.tiebe.magisterapi.api.requestPOST
import dev.tiebe.magisterapi.response.TokenResponse
import dev.tiebe.magisterapi.utils.LoginUtility.generateCodeChallenge
import dev.tiebe.magisterapi.utils.LoginUtility.generateCodeVerifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object LoginFlow {
    @Serializable
    class AuthURL(val url: String, val codeVerifier: String)

    @OptIn(ExperimentalUuidApi::class)
    fun createAuthURL(): AuthURL {
        val codeVerifier: String = generateCodeVerifier()
        val codeChallenge: String = generateCodeChallenge(codeVerifier)
        val state: String = Uuid.random().toString()
        val nonce: String = Uuid.random().toString()

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
    private const val clientId = "M6LOAPP"
    private const val scope = "openid profile email offline_access"
    private const val responseType = "id_token code"
    private const val redirectUri = "m6loapp://oauth2redirect/"
    private const val codeChallengeMethod = "S256"

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

        tokens.createdAt = Clock.System.now().epochSeconds

        return tokens
    }

}