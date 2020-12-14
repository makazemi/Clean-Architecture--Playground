package com.example.testbasalam.business.data.cache.implementation

import com.example.testbasalam.business.data.cache.abstraction.ProductCacheDataSource
import com.example.testbasalam.framework.datasource.cache.abstraction.ProductDaoService
import com.example.testbasalam.business.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCacheDataSourceImpl(
    private val productDaoService: ProductDaoService
): ProductCacheDataSource {
    override suspend fun insertProduct(item: Product): Long {
       return productDaoService.insertProduct(item)
    }

    override suspend fun deleteProduct(primaryKey: String): Int {
        return productDaoService.deleteProduct(primaryKey)
    }

    override suspend fun deleteProducts(items: List<Product>): Int {
        return productDaoService.deleteProducts(items)
    }

    override suspend fun searchProductsOrderByNameASC(
        query: String,
        page: Int
    ): List<Product> {
        return productDaoService.searchProductsOrderByNameASC(query,page)
    }

    override suspend fun getAllProducts(): List<Product> {
       return productDaoService.getAllProducts()
    }

    override suspend fun insertProducts(items: List<Product>): LongArray {
        return productDaoService.insertProducts(items)
    }
}
