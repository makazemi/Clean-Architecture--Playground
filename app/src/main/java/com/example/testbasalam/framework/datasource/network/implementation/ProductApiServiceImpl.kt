package com.example.testbasalam.framework.datasource.network.implementation

import com.example.testbasalam.framework.datasource.network.api.MainApiService
import com.example.testbasalam.framework.datasource.network.abstraction.ProductApiService
import com.example.testbasalam.framework.datasource.network.mappers.NetworkMapper
import com.example.testbasalam.business.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductApiServiceImpl
(
    private val mainApiService: MainApiService,
    private val networkMapper: NetworkMapper
): ProductApiService{
    override suspend fun insertOrUpdateProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun insertOrUpdateProducts(Products: List<Product>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(primaryKey: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeletedProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeletedProducts(Products: List<Product>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDeletedProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun getDeletedProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun searchProduct(Product: Product): Product? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): List<Product> {
        return networkMapper.entityListToProductList(mainApiService.getScores().products)
    }
}