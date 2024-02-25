package com.nicolas.ecommerce.data.datasources.local

import com.nicolas.ecommerce.data.datasources.local.localmodel.ProductLocal
import com.nicolas.ecommerce.domain.models.Product
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao,
) : ProductLocalDataSource {

    override suspend fun get(): List<Product>{
        return productDao.getAllProducts().map { it.toDomainModel() }
    }

    override suspend fun isEmpty(): Boolean = productDao.productCount() == 0

    override suspend fun save(products: List<Product>) {
        productDao.insertAllProducts(products.fromDomainModel())
    }
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