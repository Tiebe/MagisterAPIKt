package nl.tiebe.magisterapi.api

import io.ktor.http.*
import nl.tiebe.magisterapi.api.account.ProfileInfoFlow
import nl.tiebe.magisterapi.api.grades.GradeFlow
import nl.tiebe.magisterapi.response.TokenResponse
import nl.tiebe.magisterapi.response.grades.Grade
import nl.tiebe.magisterapi.response.grades.Year
import nl.tiebe.magisterapi.response.profileinfo.ProfileInfo

class Account {
    var tenantEndpoint: Url = Url("https://magister.net")
    var profileInfo: ProfileInfo? = null
    lateinit var tokens: TokenResponse
    var schoolYears: List<Year> = listOf()
    var grades: List<Grade> = listOf()
    suspend fun updateData(accessToken: String) {
        profileInfo = ProfileInfoFlow.getProfileInfo(tenantEndpoint, accessToken)
        schoolYears = GradeFlow.getYears(tenantEndpoint, accessToken, profileInfo!!.person.id)
        val tempList = mutableListOf<Grade>()
        for (year in schoolYears) {
            tempList.addAll(GradeFlow.getGrades(tenantEndpoint, accessToken, profileInfo!!.person.id, year))
        }
        grades = tempList
    }
}