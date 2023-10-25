import dev.tiebe.magisterapi.api.account.LoginFlow
import dev.tiebe.magisterapi.api.account.ProfileInfoFlow
import io.github.evanrupert.excelkt.workbook

//eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxNDRCRDYwNTlERDMyMDI5NDM1RkNDNzFFOUVEMjYwRTc2MEMzOURSUzI1NiIsIng1dCI6ImtVUzlZRm5kTWdLVU5mekhIcDdTWU9kZ3c1MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLm1hZ2lzdGVyLm5ldCIsIm5iZiI6MTY5MjI5NTc5OSwiaWF0IjoxNjkyMjk1Nzk5LCJleHAiOjE2OTIyOTkzOTksImF1ZCI6Imh0dHBzOi8vYWNjb3VudHMubWFnaXN0ZXIubmV0L3Jlc291cmNlcyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJleHRlcm5hbCJdLCJjbGllbnRfaWQiOiJNNkxPQVBQIiwic3ViIjoiNDE4ZDE2ODFlMTQyNDNmOWI2NGJmOWQyNTE3YjVlZDEiLCJhdXRoX3RpbWUiOjE2OTIyOTU3ODQsImlkcCI6Ik51b3ZvIiwidGlkIjoiYjhiMTMwNzFmOTJiNDRkMDg5MzZlYjljOTJkNTE5YWIiLCJzaWQiOiIwRDVGQkE4RTlFRDNFMzBBMjFFRkNFQUU2RjQzRkE3NSIsImp0aSI6IjU3MEYxNjZENzRBRUU1NzEyRkMxMDFGRTFENzIzMUQyIn0.USvLg7o4J-fPEe03E0wjYMgFMcnYy7TMEY_huu9sRntRGYmMY1l5LXb_s4CjNn7gln7xh8aQRkwL-LOt9NYHlid6r8KhQ1IvZ7LV_nyOWk6h5IMPePF4YOG_pCRMfbcnvaNrD05GNNtvBU-2eLyxC86F1aCEgX09whmrTE98UGE1gEM10wjH8mgSp4JQfJ-Jfg_oRxPKS3qGinvRED6dA87ZLgbJ9B4ieQHYRT8jsmgaRyrTXLzI1zQwR6lSPmaJ-VdxTi4yEyKUy8R6LfyoYi5zXqdh8OlMXDUI9gTJ13B-HYQzr_rwy9BLwYHliOGocO3hZ2S9teQCSP5-0hTWoQ
suspend fun main() {
    lateinit var token: String

    print("Existing token? (token/n)")

    val read = readln()
    token = if (read == "n") {
        val loginFLow = LoginFlow.createAuthURL()
        println("Open the following URL in your browser: ${loginFLow.url}")
        print("Input code: ")
        val code = readln()
        val tokens = LoginFlow.exchangeTokens(code, loginFLow.codeVerifier)
        println(tokens)

        tokens.accessToken
    } else read


    val tenantUrl = ProfileInfoFlow.getTenantUrl(token)

    val contacts = ProfileInfoFlow.getContacts(tenantUrl.toString(), token)
    println(contacts.first { it.roepnaam != "" })

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
