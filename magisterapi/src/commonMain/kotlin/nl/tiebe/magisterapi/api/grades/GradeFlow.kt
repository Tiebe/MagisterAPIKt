@file:Suppress("SpellCheckingInspection", "unused")

package nl.tiebe.magisterapi.api.grades

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import nl.tiebe.magisterapi.api.requestGET
import nl.tiebe.magisterapi.response.general.year.grades.Grade
import nl.tiebe.magisterapi.response.general.year.grades.GradeInfo
import nl.tiebe.magisterapi.response.general.year.grades.GradeSemester
import nl.tiebe.magisterapi.response.general.year.Year
import nl.tiebe.magisterapi.response.general.year.grades.RecentGrade
import nl.tiebe.magisterapi.utils.format

object GradeFlow {
    private const val semesterEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferperiodenvooraanmelding" // %s = account id %s = jaar id
    private const val gradesEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferoverzichtvooraanmelding?actievePerioden=false&alleenBerekendeKolommen=false&alleenPTAKolommen=false&peildatum=%s" // %s = account id %s = jaar id %s = jaar eind
    private const val gradeEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/extracijferkolominfo/%s" // %s = account id %s = jaar id %s = cijfer kolom id
    private const val recentGradesEndpoint =
        "api/personen/%s/cijfers/laatste?top=%s&skip=%s" // %s = account id %s = amount %s = skip first

    suspend fun getSemesters(tenantUrl: Url, accessToken: String, accountId: Int, year: Year): List<GradeSemester> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                semesterEndpoint.format(
                    accountId,
                    year.id
                )
            ).build(), hashMapOf(), accessToken
        )


        val json: JsonObject = response.body()
        val semesters = json["items"]?.let { Json.decodeFromJsonElement<List<GradeSemester>>(it) }
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

    suspend fun getRecentGrades(tenantUrl: Url, accessToken: String, accountId: Int, amount: Int, skip: Int): List<RecentGrade> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                recentGradesEndpoint.format(
                    accountId,
                    amount,
                    skip
                )
            ).build(), hashMapOf(), accessToken
        )

        val json: JsonObject = response.body()

        val grades = json["items"]?.let { Json.decodeFromJsonElement<List<RecentGrade>>(it) }

        return grades ?: emptyList()
    }

}


