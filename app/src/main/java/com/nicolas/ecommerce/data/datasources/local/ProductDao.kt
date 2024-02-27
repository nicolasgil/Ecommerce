package com.nicolas.ecommerce.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolas.ecommerce.data.datasources.local.localmodel.CategoriesLocal
import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productLocal: List<ProductLocal>)

    @Query("SELECT * FROM products_table ORDER BY rating DESC")
    suspend fun getAllProducts(): List<ProductLocal>

    @Query("SELECT COUNT(id) FROM products_table")
    suspend fun productCount(): Int

    @Query("DELETE FROM products_table")
    suspend fun deleteAllProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoriesLocal>)

    @Query("SELECT * FROM CategoriesLocal")
    suspend fun getCategories(): List<CategoriesLocal>

    @Query("SELECT COUNT(id) FROM CategoriesLocal")
    suspend fun categoriesCount(): Int

    @Query("DELETE FROM CategoriesLocal")
    suspend fun deleteCategories()

}
