package com.example.testbasalam.framework.datasource.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class  ProductCacheEntity(
    @PrimaryKey
    val id:String,
    val name:String,
    val photo:String,
    val vendor:String,
    val weight:Float,
    val price:Float,
    val rate:Float,
    val ratingCount:String
)