package com.nicolas.ecommerce.domain.usecases

import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.sortByRatingDescThenById
import javax.inject.Inject

class GetCategoriesUseCase@Inject constructor(
    private val productRepository: ProductRepository

) {
    suspend operator fun invoke(): List<String> {
        return productRepository.getCategories()
    }
}