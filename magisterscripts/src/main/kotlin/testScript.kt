import dev.tiebe.magisterapi.api.account.LoginFlow
import dev.tiebe.magisterapi.api.account.ProfileInfoFlow
import dev.tiebe.magisterapi.response.assignment.Assignment
import dev.tiebe.magisterapi.response.learningresource.LearningResource
import dev.tiebe.magisterapi.response.studyguide.StudyGuide
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

//eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxNDRCRDYwNTlERDMyMDI5NDM1RkNDNzFFOUVEMjYwRTc2MEMzOURSUzI1NiIsIng1dCI6ImtVUzlZRm5kTWdLVU5mekhIcDdTWU9kZ3c1MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLm1hZ2lzdGVyLm5ldCIsIm5iZiI6MTY5MjgyMjE4MCwiaWF0IjoxNjkyODIyMTgwLCJleHAiOjE2OTI4MjU3ODAsImF1ZCI6Imh0dHBzOi8vYWNjb3VudHMubWFnaXN0ZXIubmV0L3Jlc291cmNlcyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJleHRlcm5hbCJdLCJjbGllbnRfaWQiOiJNNkxPQVBQIiwic3ViIjoiNDE4ZDE2ODFlMTQyNDNmOWI2NGJmOWQyNTE3YjVlZDEiLCJhdXRoX3RpbWUiOjE2OTI4MjIxNTEsImlkcCI6Ik51b3ZvIiwidGlkIjoiYjhiMTMwNzFmOTJiNDRkMDg5MzZlYjljOTJkNTE5YWIiLCJzaWQiOiIwOTNFREI2NUYwQzMxNkM1NERFRkFENjg5ODc2ODdGNCIsImp0aSI6IjE0QTExQjRFQzYzQTkzQURDQjg0N0M0MjYwRDEwRjk1In0.JH4IZc_-XLGuIU_L-_UgaJsPRUPtjBzfQtCFQ5AbZbxmtsCxqYHx1-vcs2Vvb_PzLRQ1avN_c4h7rbbl0Ge6bKdzSZKg9RQ3cKHOSDHlqb1ATNEddboxDJl4JU4nx4t6uE4DQvPC0jQcB5YvQBMnlN1trmdO3xiFxpzx0pNd71JFpLscyfxJnhYONb_nuGeXCBuNkxdgesgHj4nfmOTilHd0FPRfdxxJbGiliM3YK-7QHA9WRpi0Zb9QgkWQ4C_XVdI1QDJDg_m4Ll0Iv_4VLb-fTzB3tlfzxQ9_ztUFVa5ob4ypPbfwf-yQIBgmAo9Bk-gpVDfnelRlRY89M9iu-g

const val jsonData = """{"Items":[{"Id":20639,"Links":[{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20639"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20740"}],"Van":"2023-08-20T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Aardrijkskunde V2","InLeerlingArchief":false},{"Id":20740,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20639"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20740"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20549"}],"Van":"2023-08-21T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Biologie klas 2","InLeerlingArchief":false},{"Id":20549,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20740"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20549"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20929"}],"Van":"2023-08-22T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Duits 2hv","InLeerlingArchief":false},{"Id":20929,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20549"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20929"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20628"}],"Van":"2023-08-29T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Frans planner periode 1 jaar 2 vwo","InLeerlingArchief":false},{"Id":20628,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20929"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20628"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20829"}],"Van":"2023-08-20T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"NaSk | VG 2","InLeerlingArchief":false},{"Id":20829,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20628"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20829"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20663"}],"Van":"2023-08-23T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"NaSk A2b periode 1","InLeerlingArchief":false},{"Id":20663,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20829"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20663"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20686"}],"Van":"2023-08-20T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Nederlands 2 havo/vwo 2023-2024","InLeerlingArchief":false},{"Id":20686,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20663"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20686"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers/20865"}],"Van":"2023-08-21T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"V2/G2","InLeerlingArchief":false},{"Id":20865,"Links":[{"Rel":"Prev","Href":"/api/leerlingen/157833/studiewijzers/20686"},{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers/20865"}],"Van":"2023-08-24T22:00:00.0000000Z","TotEnMet":"2024-07-30T22:00:00.0000000Z","VakCodes":[],"Titel":"Wiskunde A2","InLeerlingArchief":false}],"TotalCount":9,"Links":[{"Rel":"Self","Href":"/api/leerlingen/157833/studiewijzers?peildatum=2024-01-31&top=250&skip=0"},{"Rel":"Next","Href":"/api/leerlingen/157833/studiewijzers?peildatum=2024-01-31&top=250&skip=250"}]}"""

fun main() {
     val json = Json.parseToJsonElement(jsonData).jsonObject
    val assignments = json["Items"]?.let { Json.decodeFromJsonElement<List<StudyGuide>>(it) }

    println(assignments)

}
