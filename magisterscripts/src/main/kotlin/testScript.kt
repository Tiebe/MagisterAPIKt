import dev.tiebe.magisterapi.api.account.LoginFlow
import dev.tiebe.magisterapi.api.account.ProfileInfoFlow
import dev.tiebe.magisterapi.response.assignment.Assignment
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

//eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxNDRCRDYwNTlERDMyMDI5NDM1RkNDNzFFOUVEMjYwRTc2MEMzOURSUzI1NiIsIng1dCI6ImtVUzlZRm5kTWdLVU5mekhIcDdTWU9kZ3c1MCIsInR5cCI6ImF0K2p3dCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLm1hZ2lzdGVyLm5ldCIsIm5iZiI6MTY5MjgyMjE4MCwiaWF0IjoxNjkyODIyMTgwLCJleHAiOjE2OTI4MjU3ODAsImF1ZCI6Imh0dHBzOi8vYWNjb3VudHMubWFnaXN0ZXIubmV0L3Jlc291cmNlcyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJleHRlcm5hbCJdLCJjbGllbnRfaWQiOiJNNkxPQVBQIiwic3ViIjoiNDE4ZDE2ODFlMTQyNDNmOWI2NGJmOWQyNTE3YjVlZDEiLCJhdXRoX3RpbWUiOjE2OTI4MjIxNTEsImlkcCI6Ik51b3ZvIiwidGlkIjoiYjhiMTMwNzFmOTJiNDRkMDg5MzZlYjljOTJkNTE5YWIiLCJzaWQiOiIwOTNFREI2NUYwQzMxNkM1NERFRkFENjg5ODc2ODdGNCIsImp0aSI6IjE0QTExQjRFQzYzQTkzQURDQjg0N0M0MjYwRDEwRjk1In0.JH4IZc_-XLGuIU_L-_UgaJsPRUPtjBzfQtCFQ5AbZbxmtsCxqYHx1-vcs2Vvb_PzLRQ1avN_c4h7rbbl0Ge6bKdzSZKg9RQ3cKHOSDHlqb1ATNEddboxDJl4JU4nx4t6uE4DQvPC0jQcB5YvQBMnlN1trmdO3xiFxpzx0pNd71JFpLscyfxJnhYONb_nuGeXCBuNkxdgesgHj4nfmOTilHd0FPRfdxxJbGiliM3YK-7QHA9WRpi0Zb9QgkWQ4C_XVdI1QDJDg_m4Ll0Iv_4VLb-fTzB3tlfzxQ9_ztUFVa5ob4ypPbfwf-yQIBgmAo9Bk-gpVDfnelRlRY89M9iu-g

const val jsonData = """{"Items":[{"Id":20066,"Links":[{"Rel":"Self","Href":"/api/personen/157833/opdrachten/20066"}],"Titel":"3 bonuspunten ballonwagen","Vak":"ns","InleverenVoor":"2023-09-29T21:30:00.0000000Z","IngeleverdOp":"2023-09-29T19:00:05.0000000Z","StatusLaatsteOpdrachtVersie":4,"LaatsteOpdrachtVersienummer":1,"Bijlagen":[],"Docenten":null,"VersieNavigatieItems":[],"Omschrijving":"<p>De opdracht vind je op blz. 5 van het document.</p><p></p><p><em>project ballonwagen 2223 met scoreformulier.pdf</em></p>","Beoordeling":"","BeoordeeldOp":null,"OpnieuwInleveren":false,"Afgesloten":true,"MagInleveren":false},{"Id":20273,"Links":[{"Rel":"Self","Href":"/api/personen/157833/opdrachten/20273"}],"Titel":"verslag ballonwagen","Vak":"ns","InleverenVoor":"2023-11-17T22:30:00.0000000Z","IngeleverdOp":"2023-11-16T16:39:43.0000000Z","StatusLaatsteOpdrachtVersie":4,"LaatsteOpdrachtVersienummer":1,"Bijlagen":[],"Docenten":null,"VersieNavigatieItems":[],"Omschrijving":"","Beoordeling":"","BeoordeeldOp":null,"OpnieuwInleveren":false,"Afgesloten":true,"MagInleveren":false},{"Id":20581,"Links":[{"Rel":"Self","Href":"/api/personen/157833/opdrachten/20581"}],"Titel":"Recensie","Vak":"","InleverenVoor":"2023-11-23T11:00:00.0000000Z","IngeleverdOp":"2023-11-23T10:51:46.0000000Z","StatusLaatsteOpdrachtVersie":4,"LaatsteOpdrachtVersienummer":1,"Bijlagen":[],"Docenten":null,"VersieNavigatieItems":[],"Omschrijving":"<p>Je levert hier je recensie in. Ook moet je de recensie uitprinten.</p>","Beoordeling":"","BeoordeeldOp":null,"OpnieuwInleveren":true,"Afgesloten":false,"MagInleveren":false},{"Id":20876,"Links":[{"Rel":"Self","Href":"/api/personen/157833/opdrachten/20876"}],"Titel":"Nieuwe Zijderoute","Vak":"ak","InleverenVoor":"2024-02-16T11:00:00.0000000Z","IngeleverdOp":null,"StatusLaatsteOpdrachtVersie":2,"LaatsteOpdrachtVersienummer":1,"Bijlagen":[],"Docenten":null,"VersieNavigatieItems":[],"Omschrijving":"<p>In het volgende linkje kan je een lijstje vinden met landen die zijn/worden aangesloten bij de Nieuwe Zijderoute.</p><p><strong>Let op, over niet elk land is evenveel te vinden, dus als je vastloopt moet je even een ander land uitzoeken.</strong></p><p></p><p><a href=\"https://en.wikipedia.org/wiki/Belt_and_Road_Initiative#Membership\" target=\"_blank\">https://en.wikipedia.org/wiki/Belt_and_Road_Initiative#Membership</a></p>","Beoordeling":"","BeoordeeldOp":null,"OpnieuwInleveren":false,"Afgesloten":false,"MagInleveren":false}],"TotalCount":4,"Links":[{"Rel":"Self","Href":"/api/personen/157833/opdrachten?einddatum=2024-07-31&startdatum=2023-08-01&top=250&skip=0"},{"Rel":"Next","Href":"/api/personen/157833/opdrachten?einddatum=2024-07-31&startdatum=2023-08-01&top=250&skip=250"}]}"""

fun main() {
     val json = Json.parseToJsonElement(jsonData).jsonObject
    val assignments = json["Items"]?.let { Json.decodeFromJsonElement<List<Assignment>>(it) }

    println(assignments)

}
