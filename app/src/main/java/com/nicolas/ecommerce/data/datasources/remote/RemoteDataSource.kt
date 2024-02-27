package com.nicolas.ecommerce.data.datasources.remote

import com.nicolas.ecommerce.domain.models.Product

interface RemoteDataSource {

    suspend fun getAllProducts(): List<Product>
    suspend fun getCategories(): List<String>

}