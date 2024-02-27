package com.nicolas.ecommerce.data.di

import android.app.Application
import androidx.room.Room
import com.nicolas.ecommerce.data.datasources.local.ProductDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppLocalModule {

    private const val ECOMMERCE_DATABASE_NAME = "ecommerce_database"

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        ProductDataBase::class.java,
        ECOMMERCE_DATABASE_NAME,
    ).build()

    @Provides
    @Singleton
    fun provideProductDao(db: ProductDataBase) = db.productDao()
}