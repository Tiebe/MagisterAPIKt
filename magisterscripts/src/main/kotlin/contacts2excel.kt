import dev.tiebe.magisterapi.api.account.LoginFlow
import dev.tiebe.magisterapi.api.account.ProfileInfoFlow
import io.github.evanrupert.excelkt.workbook

//eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxNDRCRDYwNTlERDMyMDI5NDM1RkNDNzFFOUVEMjYwRTc2MEMzOURSUzI1NiIsIng1dCI6ImtVUzlZRm5kTWdLVU5mekhIcDdTWU9kZ3c1MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLm1hZ2lzdGVyLm5ldCIsIm5iZiI6MTY5MjI4NTgyNywiaWF0IjoxNjkyMjg1ODI3LCJleHAiOjE2OTIyODk0MjcsImF1ZCI6Imh0dHBzOi8vYWNjb3VudHMubWFnaXN0ZXIubmV0L3Jlc291cmNlcyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJleHRlcm5hbCJdLCJjbGllbnRfaWQiOiJNNkxPQVBQIiwic3ViIjoiNDE4ZDE2ODFlMTQyNDNmOWI2NGJmOWQyNTE3YjVlZDEiLCJhdXRoX3RpbWUiOjE2OTIyODU3ODksImlkcCI6Ik51b3ZvIiwidGlkIjoiYjhiMTMwNzFmOTJiNDRkMDg5MzZlYjljOTJkNTE5YWIiLCJzaWQiOiIwRDVGQkE4RTlFRDNFMzBBMjFFRkNFQUU2RjQzRkE3NSIsImp0aSI6IkY0RkREOUEwNTBDNjYxNjMyNUZDMzEwMTRGMkE3MjZBIn0.GAPOaAmP9IBs7Vez4BsTH9bEQXMz_mqlKHTAeAVc4lSNfyLErrgtPXEfZ9BP_yCG9vjHb9QTOCDVJPsf5eEadnquh2JcPvJsylbvB3A0lmwcrfrRDSW3mw5jYFQKEEcQlsM_A5XMmYz6qccCx9ezzzs5YaLmF6QtgTAo6a4o9H5Kk4Q1Bams9mkVwKlreo2twcAkdTAhxPYggHdIGQGta8PijS2MdODh2nuGLYSOL7Lz3s8ki-S53jeearDwByIRxxViA1jKeWRoTRsl2mPS7LvV0kYipFkyvttAknVDLX9FAYMwKbM7mBdejobjO_M3m2y7AcfoleJ1vVIspaQh2A

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
    val profileInfo = ProfileInfoFlow.getProfileInfo(tenantUrl.toString(), token)

    val contacts = ProfileInfoFlow.getContacts(tenantUrl.toString(), token, profileInfo.person.id)

    val classes = contacts.filter { it.type == "leerling" }.map { it.klas }.distinct()

    val classToContacts = classes.map { it to contacts.filter { contact -> contact.klas == it } }
    val teachers = contacts.filter { it.type == "medewerker" }

    workbook {
         for ((className, classContacts) in classToContacts) {
             sheet(className) {
                 for (contact in classContacts) {
                     row {
                         cell(contact.roepnaam.toString())
                         if (contact.tussenvoegsel != null) cell(contact.tussenvoegsel.toString())
                         cell(contact.achternaam)
                     }
                 }
             }
         }

        sheet("Medewerkers V5") {
            for (teacher in teachers) {
                row {
                    cell(teacher.voorletters)
                    if (teacher.tussenvoegsel != null) cell(teacher.tussenvoegsel.toString())
                    cell(teacher.achternaam)
                }
            }
        }
    }.write("contacts.xlsx")

}
