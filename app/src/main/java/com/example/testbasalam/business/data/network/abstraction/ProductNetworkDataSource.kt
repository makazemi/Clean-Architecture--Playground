package com.example.testbasalam.business.data.network.abstraction

import com.example.testbasalam.business.domain.model.Product

interface ProductNetworkDataSource {

    suspend fun insertOrUpdateProduct(product: Product)

    suspend fun deleteProduct(primaryKey: String)

    suspend fun insertDeletedProduct(product: Product)

    suspend fun insertDeletedProducts(products: List<Product>)

    suspend fun deleteDeletedProduct(product: Product)

    suspend fun getDeletedProducts(): List<Product>

    suspend fun deleteAllProducts()

    suspend fun searchProduct(product: Product): Product?

    suspend fun getAllProducts(): List<Product>

    suspend fun insertOrUpdateProducts(products: List<Product>)
}