package com.nicolas.ecommerce.data.datasources.remote

import android.util.Log
import com.nicolas.ecommerce.data.datasources.remote.remotemodel.ProductRemoteData
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService,
) : RemoteDataSource {

    override suspend fun getAllProducts(): List<Product> {
        return try {
            val response = productService.getProducts().body()
            response?.products?.toDomainModel() ?: emptyList()
        } catch (e: Exception) {
            Log.e("RemoteRemoteDataSource", "Error fetching remote products", e)
            emptyList()
        }
    }

    override suspend fun getCategories(): List<String> {
        return try {
            val response = productService.getCategories().body()
            response ?: emptyList()
        } catch (e: Exception) {
            Log.e("RemoteRemoteDataSource", "Error fetching remote categories", e)
            emptyList()
        }
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
