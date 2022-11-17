package com.fullstack.mvvm_sample.network.api

import com.fullstack.mvvm_sample.network.models.ServerError
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.Response

sealed class ResultWrapper<out T : Any?> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val error: String? = null) : ResultWrapper<Nothing>()
    data class ObjectError(val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
    data class NetworkError(val error: String? = null): ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> Response<T>): ResultWrapper<T?> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            if (result.isSuccessful){
                ResultWrapper.Success(result.body())
            } else {
                val code = result.code()
                if (result.errorBody() != null) {
                    val gson = Gson()
                    val body = result.errorBody()?.string()
                    val error = gson.fromJson(body, ServerError::class.java)
                    println(body)
                    ResultWrapper.ObjectError(code = code, error = error.message)
                }else{
                    ResultWrapper.GenericError()
                }
            }
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> ResultWrapper.NetworkError(exception.localizedMessage?.toString())
                else -> ResultWrapper.GenericError(exception.localizedMessage?.toString())
            }
        }
    }
}