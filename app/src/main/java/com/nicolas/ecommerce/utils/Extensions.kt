package com.nicolas.ecommerce.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel


fun String.toProduct(): Product {
    return Gson().fromJson(this, Product::class.java)
}

@Composable
fun  dummyProducts(): List<Product> {
    val mockProduct = LocalContext.current.resources.openRawResource(R.raw.mock_product)
        .bufferedReader().use { it.readText() }

    return List(3) { mockProduct.toProduct() }
}

@Composable
fun  dummyNavController() = rememberNavController()

@Composable
fun  dummyCategories(): List<String> {
    val mockCategories = LocalContext.current.resources.openRawResource(R.raw.mock_categories)
        .bufferedReader().use { it.readText() }

    return mockCategories.split(",").map { it.trim() }
}

enum class SortBy(val displayName: String) {
    PRICE_LOW_TO_HIGH("Price low to high"),
    PRICE_HIGH_TO_LOW("Price high to low"),
    DISCOUNT_HIGH_TO_LOW("Discount high to low"),
    RATING_HIGH_TO_LOW("Rating high to low"),
    STOCK("Stock"),
    BRAND("Brand"),
    CATEGORY_ALPHABETICALLY("Category alphabetically")
}

fun applySorting(products: List<Product>, sortBy: SortBy): List<Product> {
    return when (sortBy) {
        SortBy.PRICE_LOW_TO_HIGH -> {
            products.sortedBy { it.price }
        }

        SortBy.PRICE_HIGH_TO_LOW -> {
            products.sortedByDescending { it.price }
        }

        SortBy.DISCOUNT_HIGH_TO_LOW -> {
            products.sortedByDescending { it.discountPercentage }
        }

        SortBy.RATING_HIGH_TO_LOW -> {
            products.sortedByDescending { it.rating }
        }

        SortBy.STOCK -> {
            products.sortedByDescending { it.stock }
        }

        SortBy.BRAND -> {
            products.sortedBy { it.brand }
        }

        SortBy.CATEGORY_ALPHABETICALLY -> {
            products.sortedBy { it.category }
        }
    }
}