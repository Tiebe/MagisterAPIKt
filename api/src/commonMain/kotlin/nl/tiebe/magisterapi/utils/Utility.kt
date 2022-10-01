package nl.tiebe.magisterapi.utils

import io.ktor.http.*

/**
 * Replaces "%s" with the args in the given string.
 */
fun String.format(vararg args: Any?): String {
    var newString = this
    for (arg in args) {
        newString = newString.replaceFirst("%s", arg.toString())
    }

    return newString
}

fun String.getCodeFromUrl(): String {
    val queryPairs: MutableMap<String, String> = LinkedHashMap()
    val query = this.split("#").toTypedArray()[1]
    val pairs = query.split("&").toTypedArray()
    for (pair in pairs) {
        val idx = pair.indexOf("=")
        queryPairs[pair.substring(0, idx).decodeURLQueryComponent()] = pair.substring(idx + 1).decodeURLQueryComponent()
    }
    return queryPairs["code"] ?: throw MagisterException(HttpStatusCode.BadRequest, "No code in supplied url",
        "No code in supplied url")
}
