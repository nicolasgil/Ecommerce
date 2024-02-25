package com.nicolas.ecommerce.data.datasources.remote

import com.nicolas.ecommerce.domain.models.Product

interface ProductRemoteDataSource {

    suspend fun getAllProducts(): List<Product>

}