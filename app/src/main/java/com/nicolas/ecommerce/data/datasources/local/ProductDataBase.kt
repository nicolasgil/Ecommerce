package com.nicolas.ecommerce.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicolas.ecommerce.data.datasources.commons.funtions.Converters
import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal

@Database(entities = [ProductLocal::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}