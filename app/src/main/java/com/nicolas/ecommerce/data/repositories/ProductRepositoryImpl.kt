package com.nicolas.ecommerce.data.repositories

import android.util.Log
import com.nicolas.ecommerce.data.datasources.local.LocalDataSource
import com.nicolas.ecommerce.data.datasources.remote.RemoteDataSource
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return try {
            localDataSource.getProductList().takeIf { it.isNotEmpty() }
                ?: remoteDataSource.getAllProducts().also { localDataSource.saveProductList(it) }
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products", e)
            emptyList()
        }
    }

    override suspend fun getCategories(): List<String> {
        return try {
            localDataSource.getCategoriesList().takeIf { it.isNotEmpty() }
                ?: remoteDataSource.getCategories().also { localDataSource.saveCategoriesList(it) }
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching categories", e)
            emptyList()
        }
    }
}