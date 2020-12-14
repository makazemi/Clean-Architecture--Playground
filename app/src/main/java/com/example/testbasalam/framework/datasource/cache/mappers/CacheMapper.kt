package com.example.testbasalam.framework.datasource.cache.mappers

import com.example.testbasalam.business.domain.util.EntityMapper
import com.example.testbasalam.framework.datasource.cache.model.ProductCacheEntity
import com.example.testbasalam.business.domain.model.Product
import javax.inject.Inject

/**
in this simple project, ProductCacheEntity and Product model has the same fields exactly but in a real project, this might aren't the same.
 */
class CacheMapper
@Inject
constructor() : EntityMapper<ProductCacheEntity, Product> {

    fun entityListToProductList(entities: List<ProductCacheEntity>): List<Product> {
        val list: ArrayList<Product> = ArrayList()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun productListToEntityList(notes: List<Product>): List<ProductCacheEntity> {
        val entities: ArrayList<ProductCacheEntity> = ArrayList()
        for (note in notes) {
            entities.add(mapToEntity(note))
        }
        return entities
    }

    override fun mapFromEntity(entity: ProductCacheEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            photo = entity.photo,
            vendor = entity.vendor,
            weight = entity.weight,
            price = entity.price,
            rate = entity.rate,
            ratingCount = entity.ratingCount
        )
    }

    override fun mapToEntity(domainModel: Product): ProductCacheEntity {
        return ProductCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            photo = domainModel.photo,
            vendor = domainModel.vendor,
            weight = domainModel.weight,
            price = domainModel.price,
            rate = domainModel.rate,
            ratingCount = domainModel.ratingCount
        )
    }
}







