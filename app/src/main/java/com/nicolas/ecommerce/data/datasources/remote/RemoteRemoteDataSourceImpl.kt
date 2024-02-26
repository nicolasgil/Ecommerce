package com.nicolas.ecommerce.data.datasources.remote

import com.nicolas.ecommerce.data.datasources.remote.remotemodel.ProductRemoteData
import com.nicolas.ecommerce.domain.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService,
) : RemoteDataSource {
    override suspend fun getAllProducts(): List<Product>  {
        val response = productService.getProducts().body()
        return response?.products?.toDomainModel() ?: emptyList()
    }

    override suspend fun getCategories(): List<String> {
        val response = productService.getCategories().body()
        return response?: emptyList()
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