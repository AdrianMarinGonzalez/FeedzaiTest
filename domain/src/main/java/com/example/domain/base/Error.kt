package com.example.domain.base


sealed class Error(val message: String) {
    class UncompletedOperation(message: String = "") : Error(message)
    class UnmappableEntity(message: String = "") : Error(message)
    class NotFound(message: String = "") : Error(message)
}
