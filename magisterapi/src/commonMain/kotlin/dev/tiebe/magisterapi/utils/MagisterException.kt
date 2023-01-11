package dev.tiebe.magisterapi.utils

import io.ktor.http.*

class MagisterException(val statusCode: HttpStatusCode, val error: String?, message: String?) : RuntimeException(message)