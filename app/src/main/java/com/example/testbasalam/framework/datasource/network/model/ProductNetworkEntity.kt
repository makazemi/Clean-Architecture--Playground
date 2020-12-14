package com.example.testbasalam.framework.datasource.network.model

data class ProductNetworkEntity(
    val id:String,
    val name:String,
    val photo:String,
    val vendor:String,
    val weight:Float,
    val price:Float,
    val rate:Float,
    val ratingCount:String
)