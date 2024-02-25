package com.nicolas.ecommerce.data.datasources.local

import com.nicolas.ecommerce.domain.models.Product

interface ProductLocalDataSource {


    suspend fun get(): List<Product>
    suspend fun isEmpty(): Boolean
    suspend fun save(products: List<Product>)
}