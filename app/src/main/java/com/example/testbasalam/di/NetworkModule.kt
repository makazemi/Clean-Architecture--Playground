package com.example.testbasalam.di

import com.example.testbasalam.business.data.network.abstraction.ProductNetworkDataSource
import com.example.testbasalam.business.data.network.implementation.ProductNetworkDataSourceImpl
import com.example.testbasalam.framework.datasource.network.abstraction.ProductApiService
import com.example.testbasalam.framework.datasource.network.api.MainApiService
import com.example.testbasalam.framework.datasource.network.implementation.ProductApiServiceImpl
import com.example.testbasalam.framework.datasource.network.mappers.NetworkMapper
import com.example.testbasalam.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApiService(retrofit: Retrofit): MainApiService {
        return retrofit
            .create(MainApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkMapper()= NetworkMapper()

    @Singleton
    @Provides
    fun provideProductApiService(mainApiService: MainApiService,networkMapper: NetworkMapper):ProductApiService = ProductApiServiceImpl(mainApiService,networkMapper)

    @Singleton
    @Provides
    fun provideProductNetworkDataSource(productApiService: ProductApiService): ProductNetworkDataSource = ProductNetworkDataSourceImpl(productApiService)

}