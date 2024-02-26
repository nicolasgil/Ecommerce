package com.nicolas.ecommerce.data.datasources.local

import com.nicolas.ecommerce.data.datasources.local.localmodel.CategoriesLocal
import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao,
) : LocalDataSource {

    override suspend fun getProductList(): List<Product> {
        return productDao.getAllProducts().map { it.toDomainModel() }
    }

    override suspend fun productListIsEmpty(): Boolean = productDao.productCount() == 0

    override suspend fun saveProductList(products: List<Product>) {
        productDao.insertAllProducts(products.fromDomainModel())
    }

    override suspend fun getCategoriesList(): List<String> {
        return productDao.getCategories().toCategoriesList()
    }

    override suspend fun categoriesListIsEmpty(): Boolean = productDao.categoriesCount() == 0

    override suspend fun saveCategoriesList(categories: List<String>) {
        productDao.insertCategories(listOf(categories.toCategoriesLocal()))
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