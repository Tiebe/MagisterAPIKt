package nl.tiebe.magisterapi.utils

object Utility {
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



}