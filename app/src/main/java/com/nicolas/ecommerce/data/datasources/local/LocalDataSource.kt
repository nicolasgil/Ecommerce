package com.nicolas.ecommerce.data.datasources.local

import com.nicolas.ecommerce.domain.models.Product

interface LocalDataSource {

    suspend fun getProductList(): List<Product>
    suspend fun productListIsEmpty(): Boolean
    suspend fun saveProductList(products: List<Product>)
    suspend fun getCategoriesList(): List<String>
    suspend fun categoriesListIsEmpty(): Boolean
    suspend fun saveCategoriesList(categories: List<String>)
}