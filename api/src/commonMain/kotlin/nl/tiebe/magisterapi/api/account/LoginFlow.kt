package nl.tiebe.magisterapi.api.account

import com.benasher44.uuid.uuid4
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import nl.tiebe.magisterapi.api.Account
import nl.tiebe.magisterapi.api.Flow
import nl.tiebe.magisterapi.response.TokenResponse
import nl.tiebe.magisterapi.utils.LoginUtility.generateCodeChallenge
import nl.tiebe.magisterapi.utils.LoginUtility.generateCodeVerifier
import nl.tiebe.magisterapi.utils.MagisterException

class LoginFlow(account: Account) : Flow(account) {
    private val codeVerifier: String = generateCodeVerifier()
    private val codeChallenge: String = generateCodeChallenge(codeVerifier)
    private val state: String = uuid4().toString()
    private val nonce: String = uuid4().toString()

    fun createAuthURL(): String {
        return "${authorizationEndpoint}?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=${
            responseType.encodeURLParameter()}&scope=${scope.encodeURLParameter()}&state=${state.encodeURLParameter()}&nonce=${nonce.encodeURLParameter()}&code_challenge=${codeChallenge.encodeURLParameter()}&code_challenge_method=${
            codeChallengeMethod.encodeURLParameter()}&prompt=select_account"
    }

    suspend fun exchangeTokens(code: String): TokenResponse {
        val body: HashMap<String, String> =
            hashMapOf(
                "grant_type" to "authorization_code",
                "code" to code,
                "redirect_uri" to redirectUri,
                "client_id" to clientId,
                "code_verifier" to codeVerifier
            )
        return getToken(this.account, body)
    }

    companion object {
        private val authorizationEndpoint: Url = Url("https://accounts.magister.net/connect/authorize")
        private val tokenEndpoint: Url = Url("https://accounts.magister.net/connect/token")
        const val clientId = "M6LOAPP"
        const val scope = "openid profile email offline_access"
        const val responseType = "id_token code"
        const val redirectUri = "m6loapp://oauth2redirect/"
        const val codeChallengeMethod = "S256"

        suspend fun refreshToken(account: Account, refreshToken: String): TokenResponse {
            val body: HashMap<String, String> = hashMapOf(
                "grant_type" to "refresh_token",
                "refresh_token" to refreshToken,
                "client_id" to clientId
                )
            return getToken(account, body)
        }

        private suspend fun getToken(
            account: Account,
            body: HashMap<String, String>
        ): TokenResponse {
            val response: HttpResponse = requestPOST(tokenEndpoint, body)
            account.tokens = response.body()

            account.tokens.createdAt = Clock.System.now().epochSeconds
            val tenantResponse: HttpResponse = requestGET(
                Url("https://magister.net/.well-known/host-meta.json?rel=magister-api"),
                hashMapOf(),
                account.tokens.accessToken
            )
            val tenantUrl: String = tenantResponse.body<JsonElement>().jsonObject["links"]?.jsonArray?.get(0)?.jsonObject?.get("href")?.jsonPrimitive?.content
                ?: throw MagisterException("No tenant found")

            account.tenantEndpoint =
                Url(Url(tenantUrl).protocolWithAuthority)
            return account.tokens
        }
    }

}