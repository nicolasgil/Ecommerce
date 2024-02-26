package com.nicolas.ecommerce.domain.usecases

import android.util.Log
import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.sortByRatingDescThenById
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return try {
            productRepository.getAllProducts().sortByRatingDescThenById()
        } catch (e: Exception) {
            Log.e("GetProductsUseCase", "Error fetching products", e)
            emptyList()
        }
    }
}