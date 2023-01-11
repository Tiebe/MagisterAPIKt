package dev.tiebe.magisterapi.api.account

import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.profileinfo.ContactInfo
import dev.tiebe.magisterapi.response.profileinfo.ProfileInfo
import dev.tiebe.magisterapi.utils.MagisterException
import dev.tiebe.magisterapi.utils.format
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ProfileInfoFlow {
    private const val profileInfoEndpoint = "api/account"
    private const val profileImageEndpoint = "api/leerlingen/%s/foto?redirect_type=body" // %s = account id
    private const val contactInfoEndpoint = "api/personen/%s/profiel" // %s = account id

    suspend fun getTenantUrl(accessToken: String): Url {
        val tenantResponse: HttpResponse = requestGET(
            Url("https://magister.net/.well-known/host-meta.json?rel=magister-api"),
            hashMapOf(),
            accessToken
        )
        return Url(Url(tenantResponse.body<JsonElement>().jsonObject["links"]?.jsonArray?.get(0)?.jsonObject?.get("href")?.jsonPrimitive?.content
            ?: throw MagisterException(HttpStatusCode.NotFound, "Tenant not found",
                "Tenant not found")
        ).protocolWithAuthority)
    }

    suspend fun getProfileInfo(tenantUrl: String, accessToken: String): ProfileInfo {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                profileInfoEndpoint
            ).build(), hashMapOf(), accessToken
        )
        return response.body()
    }

    suspend fun getProfileImage(tenantUrl: String, accessToken: String, accountId: Int): ByteArray {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(profileImageEndpoint.format(accountId)).build(),
            hashMapOf(),
            accessToken
        )
        return response.body()
    }

    suspend fun getContactInfo(tenantUrl: String, accessToken: String, accountId: Int): ContactInfo {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(contactInfoEndpoint.format(accountId)).build(),
            hashMapOf(),
            accessToken
        )
        return response.body()
    }
}