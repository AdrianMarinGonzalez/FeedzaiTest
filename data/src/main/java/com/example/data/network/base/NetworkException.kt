package com.example.data.network.base


sealed class NetworkException(val code: Int, message: String?) : Exception(message) {

    class InternalServerError(message: String?) : NetworkException(500, message)
    class ServiceUnavailable(message: String?) : NetworkException(503, message)
    class NotFound(message: String?) : NetworkException(404, message)
    class TimeOut(message: String?) : NetworkException(408, message)
    class BadRequest(message: String?) : NetworkException(400, message)
    class UnmappedException(code: Int, message: String?) : NetworkException(code, message)
}