package com.example.testbasalam.business.data.network.implementation

import com.example.testbasalam.business.data.network.abstraction.ProductNetworkDataSource
import com.example.testbasalam.framework.datasource.network.abstraction.ProductApiService
import com.example.testbasalam.business.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductNetworkDataSourceImpl
(
    private val productApiService: ProductApiService
): ProductNetworkDataSource {
    override suspend fun insertOrUpdateProduct(product: Product) {
        productApiService.insertOrUpdateProduct(product)
    }

    override suspend fun deleteProduct(primaryKey: String) {
        productApiService.deleteProduct(primaryKey)
    }

    override suspend fun insertDeletedProduct(product: Product) {
        productApiService.insertDeletedProduct(product)
    }

    override suspend fun insertDeletedProducts(products: List<Product>) {
        productApiService.insertDeletedProducts(products)
    }

    override suspend fun deleteDeletedProduct(product: Product) {
        productApiService.deleteDeletedProduct(product)
    }

    override suspend fun getDeletedProducts(): List<Product> {
       return productApiService.getDeletedProducts()
    }

    override suspend fun deleteAllProducts() {
        productApiService.deleteAllProducts()
    }

    override suspend fun searchProduct(product: Product): Product? {
        return productApiService.searchProduct(product)
    }

    override suspend fun getAllProducts(): List<Product> {
        return productApiService.getAllProducts()
    }

    override suspend fun insertOrUpdateProducts(products: List<Product>) {
        productApiService.insertOrUpdateProducts(products)
    }
}