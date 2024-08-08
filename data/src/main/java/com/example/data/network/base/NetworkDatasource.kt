package com.example.data.network.base

import com.example.domain.base.Failure
import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import retrofit2.Call


open class NetworkDatasource {

    fun <T> executeCall(call: Call<T>): OperationResult<T, NetworkError> {
        return try {
            val response = call.execute()
            if (response.isSuccessful && response.body() != null) {
                Success(response.body()!!)
            } else {
                Failure(NetworkError.UnmappedError(-1, ""))
            }
        } catch (e: NetworkException) {
            Failure(mapNetworkError(e))
        } catch (e: Exception) {
            Failure(NetworkError.UnmappedError(-1, ""))
        }
    }
    
    fun mapNetworkError(exception: NetworkException): NetworkError = when (exception) {
        is NetworkException.InternalServerError -> NetworkError.InternalServerError(
            exception.localizedMessage ?: ""
        )
        is NetworkException.ServiceUnavailable -> NetworkError.ServiceUnavailable(
            exception.localizedMessage ?: ""
        )
        is NetworkException.NotFound -> NetworkError.NotFound(exception.localizedMessage ?: "")
        is NetworkException.BadRequest -> NetworkError.BadRequest(exception.localizedMessage ?: "")
        is NetworkException.TimeOut -> NetworkError.TimeOut(exception.localizedMessage ?: "")
        else -> NetworkError.UnmappedError(exception.code, exception.localizedMessage ?: "")
    }
}
