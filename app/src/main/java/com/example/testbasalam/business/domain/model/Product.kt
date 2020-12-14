package com.example.testbasalam.business.domain.model

data class Product(
    val id:String,
    val name:String,
    val photo:String,
    val vendor:String,
    val weight:Float,
    val price:Float,
    val rate:Float,
    val ratingCount:String
)

fun Product.setPostfixPrice(postfix:String):String = "$price $postfix"
fun Product.setPostfixWeight(postfix: String):String = "$weight $postfix"

fun Product.setRate():String = "$rate ($ratingCount)"