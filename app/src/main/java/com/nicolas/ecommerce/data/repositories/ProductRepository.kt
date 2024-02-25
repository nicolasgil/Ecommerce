package com.nicolas.ecommerce.data.repositories

import com.nicolas.ecommerce.domain.models.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
}