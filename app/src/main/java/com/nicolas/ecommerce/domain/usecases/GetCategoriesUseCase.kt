package com.nicolas.ecommerce.domain.usecases

import android.util.Log
import com.nicolas.ecommerce.data.repositories.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<String> {
        return try {
            productRepository.getCategories()
        } catch (e: Exception) {
            Log.e("GetCategoriesUseCase", "Error fetching categories", e)
            emptyList()
        }
    }
}
