package com.example.testbasalam.business.interactors.productList

import com.example.testbasalam.business.data.cache.abstraction.ProductCacheDataSource
import com.example.testbasalam.business.data.network.abstraction.ProductNetworkDataSource
import com.example.testbasalam.business.data.util.NetworkBoundResource
import com.example.testbasalam.business.data.util.safeCacheCall
import com.example.testbasalam.business.domain.model.Product
import com.example.testbasalam.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow


class GetProductList(
    private val productCacheDataSource: ProductCacheDataSource,
    private val productNetworkDataSource: ProductNetworkDataSource
) {

    fun getAllProducts(): Flow<DataState<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<Product>, List<Product>>(
            apiCall = {
               productNetworkDataSource.getAllProducts()
            },
            cacheCall = { productCacheDataSource.getAllProducts() }
        ) {
            override suspend fun updateCache(networkObject: List<Product>?) {
                safeCacheCall {
                    networkObject?.let {
                        productCacheDataSource.insertProducts(it)
                    }

                }
            }

            override suspend fun handleCacheSuccess(
                resultObj: List<Product>?,
                isServerSuccess: Boolean
            ): DataState<List<Product>> {
                return DataState.data(resultObj)
            }


        }.result
}