package com.nicolas.ecommerce.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicolas.ecommerce.data.datasources.local.localmodel.CategoriesLocal
import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal
import com.nicolas.ecommerce.utils.Converters

@Database(
    entities = [ProductLocal::class, CategoriesLocal::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}