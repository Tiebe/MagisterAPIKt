@file:Suppress("SpellCheckingInspection", "unused")

package dev.tiebe.magisterapi.api.grades

import dev.tiebe.magisterapi.api.json
import dev.tiebe.magisterapi.api.requestGET
import dev.tiebe.magisterapi.response.general.year.Year
import dev.tiebe.magisterapi.response.general.year.grades.Grade
import dev.tiebe.magisterapi.response.general.year.grades.GradeInfo
import dev.tiebe.magisterapi.response.general.year.grades.GradeSemester
import dev.tiebe.magisterapi.response.general.year.grades.RecentGrade
import dev.tiebe.magisterapi.utils.format
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object GradeFlow {
    private const val semesterEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferperiodenvooraanmelding" // %s = account id %s = jaar id
    private const val gradesEndpoint =
        "api/personen/%s/aanmeldingen/%s/cijfers/cijferoverzichtvooraanmelding?actievePerioden=false&alleenBerekendeKolommen=false&alleenPTAKolommen=%s&peildatum=%s" // %s = account id %s = jaar id %s alleen pta %s = jaar eind
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


        val jsonData: JsonObject = response.body()
        val semesters = jsonData["items"]?.let { json.decodeFromJsonElement<List<GradeSemester>>(it) }
        return semesters ?: emptyList()
    }

    suspend fun getGrades(tenantUrl: Url, accessToken: String, accountId: Int, year: Year, onlyPTA: Boolean = false): List<Grade> {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                gradesEndpoint.format(
                    accountId,
                    year.id,
                    onlyPTA.toString(),
                    year.end
                )
            ).build(), hashMapOf(), accessToken
        )

        val jsonData: JsonObject = response.body()

        val grades = jsonData["Items"]?.let { json.decodeFromJsonElement<List<Grade>>(it) }

        for (grade in grades ?: emptyList()) {
            grade.yearId = year.id
        }

        return grades ?: emptyList()
    }

    suspend fun getGradeInfo(tenantUrl: Url, accessToken: String, accountId: Int, grade: Grade): GradeInfo {
        val response = requestGET(
            URLBuilder(tenantUrl).appendEncodedPathSegments(
                gradeEndpoint.format(
                    accountId,
                    grade.yearId,
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

        val jsonData: JsonObject = response.body()

        val grades = jsonData["items"]?.let { json.decodeFromJsonElement<List<RecentGrade>>(it) }

        return grades ?: emptyList()
    }

}


