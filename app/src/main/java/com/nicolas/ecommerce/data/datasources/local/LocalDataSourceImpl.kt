package com.nicolas.ecommerce.data.datasources.local

import android.util.Log
import com.nicolas.ecommerce.data.datasources.local.localmodel.CategoriesLocal
import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao,
) : LocalDataSource {

    override suspend fun getProductList(): List<Product> {
        return try {
            productDao.getAllProducts().map { it.toDomainModel() }
        } catch (e: Exception) {
            Log.e("LocalDataSourceImpl", "Error fetching products from local database", e)
            emptyList()
        }
    }

    override suspend fun productListIsEmpty(): Boolean = productDao.productCount() == 0

    override suspend fun saveProductList(products: List<Product>) {
        try {
            productDao.insertAllProducts(products.fromDomainModel())
        } catch (e: Exception) {
            Log.e("LocalDataSourceImpl", "Error saving products to local database", e)
        }
    }

    override suspend fun getCategoriesList(): List<String> {
        return try {
            productDao.getCategories().toCategoriesList()
        } catch (e: Exception) {
            Log.e("LocalDataSourceImpl", "Error fetching categories from local database", e)
            emptyList()
        }
    }

    override suspend fun categoriesListIsEmpty(): Boolean = productDao.categoriesCount() == 0

    override suspend fun saveCategoriesList(categories: List<String>) {
        try {
            productDao.insertCategories(listOf(categories.toCategoriesLocal()))
        } catch (e: Exception) {
            Log.e("LocalDataSourceImpl", "Error saving categories to local database", e)
        }
    }
}

fun List<String>.toCategoriesLocal(): CategoriesLocal {
    return CategoriesLocal(categories = this)
}

fun List<CategoriesLocal>.toCategoriesList(): List<String> {
    return flatMap { it.categories }
}

private fun ProductLocal.toDomainModel(): Product = Product(
    id,
    title,
    description,
    price,
    discountPercentage,
    rating,
    stock,
    brand,
    category,
    thumbnail,
    images
)

private fun List<Product>.fromDomainModel(): List<ProductLocal> = map { it.fromDomainModel() }

private fun Product.fromDomainModel(): ProductLocal = ProductLocal(
    id,
    title,
    description,
    price,
    discountPercentage,
    rating,
    stock,
    brand,
    category,
    thumbnail,
    images
)