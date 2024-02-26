package com.nicolas.ecommerce.data.datasources.local.localmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class ProductLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "images") val images: List<String>
)


@Entity
data class CategoriesLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "categories") val categories: List<String>
)
