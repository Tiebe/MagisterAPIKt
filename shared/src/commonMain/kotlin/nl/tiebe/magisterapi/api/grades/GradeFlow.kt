package nl.tiebe.magisterapi.api.grades

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import nl.tiebe.magisterapi.api.Account
import nl.tiebe.magisterapi.api.Flow
import nl.tiebe.magisterapi.response.grades.Grade
import nl.tiebe.magisterapi.response.grades.GradeInfo
import nl.tiebe.magisterapi.response.grades.Semester
import nl.tiebe.magisterapi.response.grades.Year
import nl.tiebe.magisterapi.utils.MagisterException
import nl.tiebe.magisterapi.utils.Utility.format

class GradeFlow(account: Account) : Flow(account) {
    companion object {
        const val yearsEndpoint =
            "api/leerlingen/%s/aanmeldingen?begin=1970-01-01" // %s = account id
        const val semesterEndpoint =
            "api/personen/%s/aanmeldingen/%s/cijfers/cijferperiodenvooraanmelding" // %s = account id %s = jaar id
        const val gradesEndpoint =
            "api/personen/%s/aanmeldingen/%s/cijfers/cijferoverzichtvooraanmelding?actievePerioden=false&alleenBerekendeKolommen=false&alleenPTAKolommen=false&peildatum=%s" // %s = account id %s = jaar id %s = jaar eind
        const val gradeEndpoint =
            "api/personen/%s/aanmeldingen/%s/cijfers/extracijferkolominfo/%s" // %s = account id %s = jaar id %s = cijfer kolom id
    }


    suspend fun getYears(): List<Year> {
        val response = requestGET(
            URLBuilder(mainEndpoint).appendEncodedPathSegments(
                yearsEndpoint.format(
                    account.profileInfo?.person?.id ?: throw MagisterException("No account id")
                )
            ).build(), hashMapOf(), account.tokens.accessToken
        )


        return response.body()
    }

    suspend fun getSemesters(year: Year): List<Semester> {
        val response = requestGET(
            URLBuilder(mainEndpoint).appendEncodedPathSegments(
                semesterEndpoint.format(
                    account.profileInfo?.person?.id ?: throw MagisterException("No account id"),
                    year.id
                )
            ).build(), hashMapOf(), account.tokens.accessToken
        )


        val json: JsonObject = response.body()
        val semesters = json["items"]?.let { Json.decodeFromJsonElement<List<Semester>>(it) }
        return semesters ?: emptyList()
    }

    suspend fun getGrades(year: Year): List<Grade> {
        val response = requestGET(
            URLBuilder(mainEndpoint).appendEncodedPathSegments(
                gradesEndpoint.format(
                    account.profileInfo?.person?.id ?: throw MagisterException("No account id"),
                    year.id,
                    year.end
                )
            ).build(), hashMapOf(), account.tokens.accessToken
        )

        val json: JsonObject = response.body()
        val grades = json["items"]?.let { Json.decodeFromJsonElement<List<Grade>>(it) }
        return grades ?: emptyList()
    }

    suspend fun getGradeInfo(grade: Grade): GradeInfo {
        val response = requestGET(
            URLBuilder(mainEndpoint).appendEncodedPathSegments(
                gradeEndpoint.format(
                    account.profileInfo?.person?.id ?: throw MagisterException("No account id"),
                    grade.year?.id ?: throw MagisterException("No year id"),
                    grade.gradeColumn?.id ?: throw MagisterException("No grade column id")
                )
            ).build(), hashMapOf(), account.tokens.accessToken
        )

        return response.body()
    }


}


