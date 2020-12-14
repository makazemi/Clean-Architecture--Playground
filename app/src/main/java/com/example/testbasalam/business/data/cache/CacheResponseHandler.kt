package com.example.testbasalam.business.data.cache

import com.example.testbasalam.business.domain.state.ErrorBody
import com.example.testbasalam.business.domain.state.DataState


abstract class CacheResponseHandler<ResultType, Data>(
    private val response: CacheResult<Data?>
) {
    suspend fun getResult(): DataState<ResultType> {

        return when (response) {

            is CacheResult.GenericError -> {
                DataState.error(
                    ErrorBody(message = response.errorMessage)
                )
            }

            is CacheResult.Success -> {
                handleSuccess(resultObj = response.value)
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data?): DataState<ResultType>

}
