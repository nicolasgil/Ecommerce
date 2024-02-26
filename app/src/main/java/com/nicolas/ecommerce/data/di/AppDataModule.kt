package com.nicolas.ecommerce.data.di

import com.nicolas.ecommerce.data.datasources.local.LocalDataSource
import com.nicolas.ecommerce.data.datasources.local.LocalDataSourceImpl
import com.nicolas.ecommerce.data.datasources.remote.RemoteDataSource
import com.nicolas.ecommerce.data.datasources.remote.RemoteRemoteDataSourceImpl
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
    abstract fun bindProductRemoteDataSource(productRemoteDataSourceImpl: RemoteRemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindProductLocalDataSource(productLocalDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

}