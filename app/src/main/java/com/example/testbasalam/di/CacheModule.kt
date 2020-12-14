package com.example.testbasalam.di

import android.content.Context
import androidx.room.Room
import com.example.testbasalam.business.data.cache.abstraction.ProductCacheDataSource
import com.example.testbasalam.business.data.cache.implementation.ProductCacheDataSourceImpl
import com.example.testbasalam.framework.datasource.cache.abstraction.ProductDaoService
import com.example.testbasalam.framework.datasource.cache.database.AppDatabase
import com.example.testbasalam.framework.datasource.cache.database.ProductDao
import com.example.testbasalam.framework.datasource.cache.implementation.ProductDaoServiceImpl
import com.example.testbasalam.framework.datasource.cache.mappers.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideBabyDao(db: AppDatabase): ProductDao {
        return db.getProductDao()
    }

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext application: Context): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCacheMapper()= CacheMapper()

    @Singleton
    @Provides
    fun provideProductDaoService(productDao: ProductDao,cacheMapper: CacheMapper):ProductDaoService = ProductDaoServiceImpl(productDao,cacheMapper)

    @Singleton
    @Provides
    fun provideProductCacheDataSource(productDaoService: ProductDaoService):ProductCacheDataSource = ProductCacheDataSourceImpl(productDaoService)




}