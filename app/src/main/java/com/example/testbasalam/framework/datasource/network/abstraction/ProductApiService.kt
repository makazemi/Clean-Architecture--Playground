package com.example.testbasalam.framework.datasource.network.abstraction

import com.example.testbasalam.business.domain.model.Product

interface ProductApiService {

    suspend fun insertOrUpdateProduct(Product: Product)

    suspend fun insertOrUpdateProducts(Products: List<Product>)

    suspend fun deleteProduct(primaryKey: String)

    suspend fun insertDeletedProduct(Product: Product)

    suspend fun insertDeletedProducts(Products: List<Product>)

    suspend fun deleteDeletedProduct(Product: Product)

    suspend fun deleteAllProducts()

    suspend fun getDeletedProducts(): List<Product>

    suspend fun searchProduct(Product: Product): Product?

    suspend fun getAllProducts(): List<Product>
}