package nl.tiebe.magisterapi.api.account

import com.soywiz.korio.file.std.localVfs
import com.soywiz.korio.stream.readLine
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import nl.tiebe.magisterapi.api.Account
import nl.tiebe.magisterapi.api.grades.GradeFlow
import kotlin.test.Test

class LoginFlowTest {
    private val skipTests = true
    @Test
    fun testExchangeTokens() {
        if (skipTests) return
        val account = Account()
        val loginFlow = LoginFlow(account)
        println(loginFlow.createAuthURL())
        println("Enter url: ")
        runBlocking {
            val text = waitForFileModified()
            val code = splitQuery(text)["code"]
            println(code?.let { loginFlow.exchangeTokens(it).refreshToken })
        }
    }

    @Test
    fun testRefreshTokens() {
        if (skipTests) return
        val account = Account()
        val loginFlow = LoginFlow(account)
        println(loginFlow.createAuthURL())
        println("Enter refresh token: ")
        runBlocking {
            val token = waitForFileModified()
            account.tokens = LoginFlow.refreshToken(account, token)
            println(account.tokens.refreshToken)
            getData(account)
        }
    }

    companion object {
        suspend fun getData(account: Account) {
            val profileInfoFlow = ProfileInfoFlow(account)
            account.profileInfo = profileInfoFlow.getProfileInfo()
            println(account.profileInfo!!.person.firstName)
            val gradeFlow = GradeFlow(account)
            account.schoolYears = gradeFlow.getYears()
            println(account.schoolYears[0].end)
            account.grades = gradeFlow.getGrades(account.schoolYears[0])
            println(GradeFlow(account).getGradeInfo(account.grades[0]).columnDescription)
        }

        fun splitQuery(url: String): Map<String, String> {
            val queryPairs: MutableMap<String, String> = LinkedHashMap()
            val query = url.split("#").toTypedArray()[1]
            val pairs = query.split("&").toTypedArray()
            for (pair in pairs) {
                val idx = pair.indexOf("=")
                queryPairs[pair.substring(0, idx).decodeURLQueryComponent()] = pair.substring(idx + 1).decodeURLQueryComponent()
            }
            return queryPairs
        }

        suspend fun waitForFileModified(): String {
            val file = localVfs("/tmp/test")["test.txt"]
            val startValue = file.open().readLine()
            while (true) {
                if (file.open().readLine() != startValue) {
                    return file.open().readLine()
                }
                delay(1000)
            }
        }
    }
}