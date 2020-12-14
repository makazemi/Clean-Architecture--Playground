package com.example.testbasalam.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.testbasalam.business.data.cache.abstraction.ProductCacheDataSource
import com.example.testbasalam.business.data.network.abstraction.ProductNetworkDataSource
import com.example.testbasalam.business.interactors.productList.GetProductList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext application: Context): RequestManager {
        return Glide.with(application)
    }

    @Singleton
    @Provides
    fun provideGetProductList(productCacheDataSource: ProductCacheDataSource,productNetworkDataSource: ProductNetworkDataSource) = GetProductList(productCacheDataSource,productNetworkDataSource)

}