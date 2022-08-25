package nl.tiebe.magisterapi.api.account

import io.ktor.client.call.*
import io.ktor.http.*
import nl.tiebe.magisterapi.api.Account
import nl.tiebe.magisterapi.api.Flow
import nl.tiebe.magisterapi.response.profileinfo.ProfileInfo

class ProfileInfoFlow(account: Account) : Flow(account) {
    private val profileEndpoint = URLBuilder(mainEndpoint).appendEncodedPathSegments("api/account").build()
    suspend fun getProfileInfo(): ProfileInfo {
        val response = requestGET(
            profileEndpoint,
            hashMapOf(),
            account.tokens.accessToken
        )
        return response.body()
    }
}