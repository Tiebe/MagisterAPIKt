package nl.tiebe.magisterapi.api.account

import com.benasher44.uuid.uuid4
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import nl.tiebe.magisterapi.api.requestPOST
import nl.tiebe.magisterapi.response.TokenResponse
import nl.tiebe.magisterapi.utils.LoginUtility.generateCodeChallenge
import nl.tiebe.magisterapi.utils.LoginUtility.generateCodeVerifier

object LoginFlow {
    @Serializable
    class AuthURL(val url: String, val codeVerifier: String)

    fun createAuthURL(): AuthURL {
        val codeVerifier: String = generateCodeVerifier()
        val codeChallenge: String = generateCodeChallenge(codeVerifier)
        val state: String = uuid4().toString()
        val nonce: String = uuid4().toString()

        return AuthURL("${authorizationEndpoint}?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=${
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

    private val authorizationEndpoint: Url = Url("https://accounts.magister.net/connect/authorize")
    private val tokenEndpoint: Url = Url("https://accounts.magister.net/connect/token")
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

        tokens.createdAt = Clock.System.now().epochSeconds

        return tokens
    }

}