package com.example.data.network.base


sealed class NetworkError(val code: Int, val message: String) {
    class InternalServerError(message: String) : NetworkError(500, message)
    class ServiceUnavailable(message: String) : NetworkError(503, message)
    class NotFound(message: String) : NetworkError(404, message)
    class BadRequest(message: String) : NetworkError(400, message)
    class TimeOut(message: String) : NetworkError(408, message)
    class UnmappedError(code: Int, message: String) : NetworkError(code, message)
}