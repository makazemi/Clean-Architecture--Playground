package com.example.testbasalam.framework.datasource.network.api


import com.example.testbasalam.framework.datasource.network.model.ProductsResponse
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface MainApiService {

    @GET("score")
    suspend fun getScores(
    ): ProductsResponse

}