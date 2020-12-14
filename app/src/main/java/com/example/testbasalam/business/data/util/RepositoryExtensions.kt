package com.example.testbasalam.business.data.util



import com.example.testbasalam.business.data.cache.CacheResult
import com.example.testbasalam.business.data.network.ApiResult
import com.example.testbasalam.util.Constant.NETWORK_TIMEOUT
import com.example.testbasalam.business.domain.state.ErrorBody
import com.example.testbasalam.util.ErrorHandling.Companion.ERROR_UNKNOWN
import com.example.testbasalam.business.domain.state.DataState
import com.example.testbasalam.util.Constant.CACHE_TIMEOUT
import com.example.testbasalam.util.ErrorHandling.Companion.CACHE_ERROR_TIMEOUT
import com.example.testbasalam.util.ErrorHandling.Companion.NETWORK_ERROR_TIMEOUT
import com.example.testbasalam.util.ErrorHandling.Companion.NETWORK_ERROR_UNKNOWN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T?

): ApiResult<T?>  {
    return withContext(Dispatchers.IO) {
        try {
            withTimeout(NETWORK_TIMEOUT) {
                //Timber.d( "Try")
//                Log.d( TAG,"Try")
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    ApiResult.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ApiResult.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    ApiResult.GenericError(
                        null,
                        NETWORK_ERROR_UNKNOWN
                    )

                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(
    cacheCall: suspend ()  -> T?
): CacheResult<T?> {
    return  withContext(Dispatchers.IO) {
        try {
            withTimeout(CACHE_TIMEOUT) {
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            Timber.d( "safeCacheCall call=$cacheCall and throwable=${throwable.message}")
            when (throwable) {
                is TimeoutCancellationException -> {
                    CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
                }
                else -> {
                    CacheResult.GenericError(ERROR_UNKNOWN)
                }

            }
        }
    }

}

fun <ResultType> buildError(
    message: String,
    code: String? = null
): DataState<ResultType> {
    return DataState.error(
        ErrorBody(code, message)
    )


}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        ERROR_UNKNOWN
    }
}























