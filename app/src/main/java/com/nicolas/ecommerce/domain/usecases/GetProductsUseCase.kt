package com.nicolas.ecommerce.domain.usecases

import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository

) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getAllProducts()
    }
}