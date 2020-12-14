package com.example.testbasalam.business.data.network

import com.example.testbasalam.business.data.util.buildError
import com.example.testbasalam.util.ErrorHandling
import com.example.testbasalam.util.ErrorHandling.Companion.NETWORK_DATA_NULL
import com.example.testbasalam.business.domain.state.DataState


abstract class ApiResponseHandler <ResultType, Data>(
    private val response: ApiResult<Data?>
){

    suspend fun getResult(): DataState<ResultType>? {

        return when(response){

            is ApiResult.GenericError -> {
                buildError(
                    response.errorMessage ?: ErrorHandling.ERROR_UNKNOWN
                )
            }

            is ApiResult.NetworkError -> {
                buildError(
                    ErrorHandling.NETWORK_ERROR
                )
            }

            is ApiResult.Success -> {
                if(response.value == null){
                 buildError(
                     NETWORK_DATA_NULL
                 )
                }
                else{
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ResultType>?

}