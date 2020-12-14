package com.example.testbasalam.business.data.util

import com.example.testbasalam.business.data.cache.CacheResponseHandler
import com.example.testbasalam.business.data.network.ApiResult
import com.example.testbasalam.business.domain.state.DataState
import com.example.testbasalam.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class NetworkBoundResource<NetworkObj, CacheObj, ResultType>
constructor(
    private val apiCall: suspend () -> NetworkObj?,
    private val cacheCall: suspend () -> CacheObj?
) {

    var isServerSuccess = false

    val result: Flow<DataState<ResultType>> = flow {

        emit(DataState.loading<ResultType>(true, cachedData = null))

            emit(returnCache())

        when (val apiResult = safeApiCall { apiCall.invoke() }) {

            is ApiResult.GenericError -> {
                buildError<ResultType>(apiResult.errorMessage ?: ErrorHandling.ERROR_UNKNOWN)
            }

            is ApiResult.NetworkError -> {
                buildError<ResultType>(
                    ErrorHandling.NETWORK_ERROR
                )
            }

            is ApiResult.Success -> {
                updateCache(apiResult.value)
            }
        }
        emit(returnCache())


    }

    private suspend fun returnCache(): DataState<ResultType> {

        val cacheResult = safeCacheCall { cacheCall.invoke() }


        return object : CacheResponseHandler<ResultType, CacheObj>(
            response = cacheResult
        ) {
            override suspend fun handleSuccess(resultObj: CacheObj?): DataState<ResultType> {
                return handleCacheSuccess(resultObj, isServerSuccess)
            }
        }.getResult()

    }

    abstract suspend fun updateCache(networkObject: NetworkObj?)

    abstract suspend fun handleCacheSuccess(
        resultObj: CacheObj?,
        isServerSuccess: Boolean
    ): DataState<ResultType>

}

