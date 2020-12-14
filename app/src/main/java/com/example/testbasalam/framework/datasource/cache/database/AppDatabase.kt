package com.example.testbasalam.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testbasalam.framework.datasource.cache.model.ProductCacheEntity


@Database(entities = [ProductCacheEntity::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object{
        val DATABASE_NAME: String = "product_db"
    }
}









