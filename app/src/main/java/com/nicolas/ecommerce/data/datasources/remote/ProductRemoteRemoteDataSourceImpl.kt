package com.nicolas.ecommerce.data.datasources.remote

import com.nicolas.ecommerce.data.datasources.remote.remotemodel.ProductRemoteData
import com.nicolas.ecommerce.domain.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRemoteRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService,
) : ProductRemoteDataSource {
    override suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {
        val response = productService.getProducts().body()
        return@withContext response?.products?.toDomainModel() ?: emptyList()
    }

    private fun List<ProductRemoteData>.toDomainModel(): List<Product> =
        map { it.toDomainModel() }

    private fun ProductRemoteData.toDomainModel(): Product =
        Product(
            id,
            title,
            description,
            price,
            discountPercentage,
            rating,
            stock,
            brand,
            category,
            thumbnail,
            images
        )

}