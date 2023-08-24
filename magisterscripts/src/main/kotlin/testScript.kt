import dev.tiebe.magisterapi.api.account.LoginFlow
import dev.tiebe.magisterapi.api.account.ProfileInfoFlow

//eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxNDRCRDYwNTlERDMyMDI5NDM1RkNDNzFFOUVEMjYwRTc2MEMzOURSUzI1NiIsIng1dCI6ImtVUzlZRm5kTWdLVU5mekhIcDdTWU9kZ3c1MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLm1hZ2lzdGVyLm5ldCIsIm5iZiI6MTY5MjgyMjE4MCwiaWF0IjoxNjkyODIyMTgwLCJleHAiOjE2OTI4MjU3ODAsImF1ZCI6Imh0dHBzOi8vYWNjb3VudHMubWFnaXN0ZXIubmV0L3Jlc291cmNlcyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJleHRlcm5hbCJdLCJjbGllbnRfaWQiOiJNNkxPQVBQIiwic3ViIjoiNDE4ZDE2ODFlMTQyNDNmOWI2NGJmOWQyNTE3YjVlZDEiLCJhdXRoX3RpbWUiOjE2OTI4MjIxNTEsImlkcCI6Ik51b3ZvIiwidGlkIjoiYjhiMTMwNzFmOTJiNDRkMDg5MzZlYjljOTJkNTE5YWIiLCJzaWQiOiIwOTNFREI2NUYwQzMxNkM1NERFRkFENjg5ODc2ODdGNCIsImp0aSI6IjE0QTExQjRFQzYzQTkzQURDQjg0N0M0MjYwRDEwRjk1In0.JH4IZc_-XLGuIU_L-_UgaJsPRUPtjBzfQtCFQ5AbZbxmtsCxqYHx1-vcs2Vvb_PzLRQ1avN_c4h7rbbl0Ge6bKdzSZKg9RQ3cKHOSDHlqb1ATNEddboxDJl4JU4nx4t6uE4DQvPC0jQcB5YvQBMnlN1trmdO3xiFxpzx0pNd71JFpLscyfxJnhYONb_nuGeXCBuNkxdgesgHj4nfmOTilHd0FPRfdxxJbGiliM3YK-7QHA9WRpi0Zb9QgkWQ4C_XVdI1QDJDg_m4Ll0Iv_4VLb-fTzB3tlfzxQ9_ztUFVa5ob4ypPbfwf-yQIBgmAo9Bk-gpVDfnelRlRY89M9iu-g

suspend fun main() {
    lateinit var token: String

    print("Existing token? (token/n)")

    val read = readln()
    token = if (read == "n") {
        val loginFLow = LoginFlow.createAuthURL()
        println("Open the following URL in your browser: ${loginFLow.url}")
        print("Input code: ")
        val code = readln()
        LoginFlow.exchangeTokens(code, loginFLow.codeVerifier).accessToken
    } else read


    val tenantUrl = ProfileInfoFlow.getTenantUrl(token)

    println(ProfileInfoFlow.getContact(tenantUrl.toString(), token, "/api/medewerkers/89215"))

}
