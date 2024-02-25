package com.nicolas.ecommerce.data.di

import com.nicolas.ecommerce.data.datasources.local.ProductLocalDataSource
import com.nicolas.ecommerce.data.datasources.local.ProductLocalDataSourceImpl
import com.nicolas.ecommerce.data.datasources.remote.ProductRemoteDataSource
import com.nicolas.ecommerce.data.datasources.remote.ProductRemoteRemoteDataSourceImpl
import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.data.repositories.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindProductRemoteDataSource(productRemoteDataSourceImpl: ProductRemoteRemoteDataSourceImpl): ProductRemoteDataSource

    @Binds
    abstract fun bindProductLocalDataSource(productLocalDataSourceImpl: ProductLocalDataSourceImpl): ProductLocalDataSource

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}