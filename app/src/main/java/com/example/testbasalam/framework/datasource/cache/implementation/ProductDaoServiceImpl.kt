package com.example.testbasalam.framework.datasource.cache.implementation

import com.example.testbasalam.framework.datasource.cache.abstraction.ProductDaoService
import com.example.testbasalam.framework.datasource.cache.database.ProductDao
import com.example.testbasalam.framework.datasource.cache.mappers.CacheMapper
import com.example.testbasalam.business.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDaoServiceImpl(
    private val productDao: ProductDao,
    private val productMapper:CacheMapper,
): ProductDaoService {
    override suspend fun insertProduct(item: Product): Long {
        return productDao.insertProduct(productMapper.mapToEntity(item))
    }

    override suspend fun insertProducts(items: List<Product>): LongArray {
        return productDao.insertProducts(productMapper.productListToEntityList(items))
    }

    override suspend fun searchProductById(id: String): Product? {
        return productDao.searchProductById(id)?.let { product ->
            productMapper.mapFromEntity(product)
        }
    }

    override suspend fun deleteProducts(items: List<Product>): Int {
        val ids = items.mapIndexed { _, value -> value.id}
        return productDao.deleteProducts(ids)
    }

    override suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }

    override suspend fun getAllProducts(): List<Product> {
        return productMapper.entityListToProductList(productDao.getAllProducts())
    }

    override suspend fun deleteProduct(primaryKey: String): Int {
        return productDao.deleteProduct(primaryKey)
    }

    override suspend fun searchProductsOrderByNameASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<Product> {
        return productMapper.entityListToProductList(
            productDao.searchProductsOrderByNameASC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }
}