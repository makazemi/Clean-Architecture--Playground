package com.example.testbasalam.framework.datasource.cache.abstraction

import com.example.testbasalam.framework.datasource.cache.database.PRODUCT_PAGINATION_PAGE_SIZE
import com.example.testbasalam.business.domain.model.Product

interface ProductDaoService {


    suspend fun insertProduct(item: Product): Long


    suspend fun insertProducts(items: List<Product>): LongArray


    suspend fun searchProductById(id: String): Product?


    suspend fun deleteProducts(items: List<Product>): Int


    suspend fun deleteAllProducts()


    suspend fun getAllProducts(): List<Product>



    suspend fun deleteProduct(primaryKey: String): Int


    suspend fun searchProductsOrderByNameASC(
        query: String,
        page: Int,
        pageSize: Int = PRODUCT_PAGINATION_PAGE_SIZE
    ): List<Product>
}