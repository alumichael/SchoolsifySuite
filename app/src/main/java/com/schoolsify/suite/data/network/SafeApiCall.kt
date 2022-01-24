package com.fbn.fbnquest.data.network

import android.accounts.NetworkErrorException
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {

                       // val errorMessage = getErrorApiError(throwable)
                        Resource.Failure(false, throwable.code(), "errorMessage")

                    }

                    is NetworkErrorException ->{
                        Resource.Failure(true, null, null)
                    }

                    else -> {
                        throwable.message.let { Log.i("Exception1", it.toString()) }
                        Resource.Failure(false, null, null)
                    }
                }
            }
        }
    }

   /* private fun getErrorApiError(httpException: HttpException): String? {
        var errorMessage: String? = null
        try {
            val body = httpException.response()?.errorBody()
            val adapter = Gson().getAdapter(ApiError::class.java)
            val errorParser = adapter.fromJson(body?.string())
            errorMessage = errorParser.error?.joinToString()

            Log.i("ErrorCheck",errorMessage.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return errorMessage
        }
    }*/
}