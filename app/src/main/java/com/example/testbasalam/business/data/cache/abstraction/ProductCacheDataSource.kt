package com.example.testbasalam.business.data.cache.abstraction

import com.example.testbasalam.business.domain.model.Product

interface ProductCacheDataSource {

    suspend fun insertProduct(item: Product): Long

    suspend fun deleteProduct(primaryKey: String): Int

    suspend fun deleteProducts(items: List<Product>): Int

    suspend fun searchProductsOrderByNameASC(
        query: String,
        page: Int
    ): List<Product>

    suspend fun getAllProducts(): List<Product>

    suspend fun insertProducts(items: List<Product>): LongArray

}