package nl.tiebe.magisterapi.api.account

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import nl.tiebe.magisterapi.api.requestGET
import nl.tiebe.magisterapi.response.profileinfo.ProfileInfo
import nl.tiebe.magisterapi.utils.MagisterException

object ProfileInfoFlow {
    private fun profileEndpoint(tenantUrl: Url) = URLBuilder(tenantUrl).appendEncodedPathSegments("api/account").build()

    suspend fun getTenantUrl(accessToken: String): Url {
        val tenantResponse: HttpResponse = requestGET(
            Url("https://magister.net/.well-known/host-meta.json?rel=magister-api"),
            hashMapOf(),
            accessToken
        )
        return Url(Url(tenantResponse.body<JsonElement>().jsonObject["links"]?.jsonArray?.get(0)?.jsonObject?.get("href")?.jsonPrimitive?.content
            ?: throw MagisterException(HttpStatusCode.NotFound, "Tenant not found",
                "Tenant not found")).protocolWithAuthority)
    }

    suspend fun getProfileInfo(tenantUrl: Url, accessToken: String): ProfileInfo {
        val response = requestGET(
            profileEndpoint(tenantUrl),
            hashMapOf(),
            accessToken
        )
        return response.body()
    }
}