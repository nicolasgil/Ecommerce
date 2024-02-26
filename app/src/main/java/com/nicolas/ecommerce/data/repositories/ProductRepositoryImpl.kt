package com.nicolas.ecommerce.data.repositories

import com.nicolas.ecommerce.data.datasources.local.LocalDataSource
import com.nicolas.ecommerce.data.datasources.remote.RemoteDataSource
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> =
        localDataSource.getProductList().takeIf { it.isNotEmpty() }
            ?: remoteDataSource.getAllProducts().also { localDataSource.saveProductList(it) }

    override suspend fun getCategories(): List<String> =
        localDataSource.getCategoriesList().takeIf { it.isNotEmpty() }
            ?: remoteDataSource.getCategories().also { localDataSource.saveCategoriesList(it) }


}