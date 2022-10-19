@file:Suppress("SpellCheckingInspection")

package nl.tiebe.magisterapi.api.grades

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import nl.tiebe.magisterapi.api.requestGET
import nl.tiebe.magisterapi.response.grades.Grade
import nl.tiebe.magisterapi.response.grades.GradeInfo
import nl.tiebe.magisterapi.response.grades.Semester
import nl.tiebe.magisterapi.response.grades.Year
import nl.tiebe.magisterapi.utils.format

object GradeFlow {
    private const val yearsEndpoint =
        "api/leerlingen/%s/aanmeldingen?begin=1970-01-01" // %s = account id
    private const val semesterEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferperiodenvooraanmelding" // %s = account id %s = jaar id
    private const val gradesEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferoverzichtvooraanmelding?actievePerioden=false&alleenBerekendeKolommen=false&alleenPTAKolommen=false&peildatum=%s" // %s = account id %s = jaar id %s = jaar eind
    private const val gradeEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/extracijferkolominfo/%s" // %s = account id %s = jaar id %s = cijfer kolom id



    suspend fun getYears(tenantUrl: Url, accessToken: String, accountId: Int): List<Year> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                yearsEndpoint.format(accountId)
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()
        val years = json["items"]?.let { Json.decodeFromJsonElement<List<Year>>(it) }
        return years ?: emptyList()
    }

    suspend fun getSemesters(tenantUrl: Url, accessToken: String, accountId: Int, year: Year): List<Semester> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                semesterEndpoint.format(
                    accountId,
                    year.id
                )
            ).build(), hashMapOf(), accessToken
        )


        val json: JsonObject = response.body()
        val semesters = json["items"]?.let { Json.decodeFromJsonElement<List<Semester>>(it) }
        return semesters ?: emptyList()
    }

    suspend fun getGrades(tenantUrl: Url, accessToken: String, accountId: Int, year: Year): List<Grade> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                gradesEndpoint.format(
                    accountId,
                    year.id,
                    year.end
                )
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()

        val grades = json["Items"]?.let { Json.decodeFromJsonElement<List<Grade>>(it) }

        for (grade in grades ?: emptyList()) {
            grade.year = year
        }

        return grades ?: emptyList()
    }

    suspend fun getGradeInfo(tenantUrl: Url, accessToken: String, accountId: Int, grade: Grade): GradeInfo {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                gradeEndpoint.format(
                    accountId,
                    grade.year.id,
                    grade.gradeColumn.id
                )
            ).build(), hashMapOf(), accessToken
        )

        return response.body()
    }


}


