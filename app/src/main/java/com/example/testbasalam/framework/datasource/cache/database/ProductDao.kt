package com.example.testbasalam.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbasalam.framework.datasource.cache.model.ProductCacheEntity

const val PRODUCT_PAGINATION_PAGE_SIZE = 30

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(item: ProductCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(items: List<ProductCacheEntity>): LongArray

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun searchProductById(id: String): ProductCacheEntity?

    @Query("DELETE FROM products WHERE id IN (:ids)")
    suspend fun deleteProducts(ids: List<String>): Int

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductCacheEntity>


    @Query("DELETE FROM products WHERE id = :primaryKey")
    suspend fun deleteProduct(primaryKey: String): Int

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR vendor LIKE '%' || :query || '%' ORDER BY name ASC LIMIT (:page * :pageSize)")
    suspend fun searchProductsOrderByNameASC(
        query: String,
        page: Int,
        pageSize: Int = PRODUCT_PAGINATION_PAGE_SIZE
    ): List<ProductCacheEntity>
}