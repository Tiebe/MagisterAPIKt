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
    suspend fun updateData() {
        profileInfo = ProfileInfoFlow(this).getProfileInfo()
        schoolYears = GradeFlow(this).getYears()
        val tempList = mutableListOf<Grade>()
        for (year in schoolYears) {
            tempList.addAll(GradeFlow(this).getGrades(year))
        }
        grades = tempList
    }
}