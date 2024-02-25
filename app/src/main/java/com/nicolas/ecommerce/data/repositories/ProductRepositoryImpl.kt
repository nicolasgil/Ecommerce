package com.nicolas.ecommerce.data.repositories

import com.nicolas.ecommerce.data.datasources.local.ProductLocalDataSource
import com.nicolas.ecommerce.data.datasources.remote.ProductRemoteDataSource
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> =
        localDataSource.get().takeIf { it.isNotEmpty() }
            ?: remoteDataSource.getAllProducts().also { localDataSource.save(it) }


}