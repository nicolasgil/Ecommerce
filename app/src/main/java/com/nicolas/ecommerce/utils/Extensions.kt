package com.nicolas.ecommerce.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product


fun String.toProduct(): Product {
    return Gson().fromJson(this, Product::class.java)
}

fun List<Product>.sortByRatingDescThenById(): List<Product> =
    sortedWith(compareByDescending<Product> { it.rating }.thenBy { it.id })

@Composable
fun loadSampleProducts(): List<Product> {
    val mockProduct = LocalContext.current.resources.openRawResource(R.raw.mock_product)
        .bufferedReader().use { it.readText() }

    return List(3) { mockProduct.toProduct() }
}

@Composable
fun loadSampleNavController() = rememberNavController()

@Composable
fun loadSampleCategories(): List<String> {
    val mockCategories = LocalContext.current.resources.openRawResource(R.raw.mock_categories)
        .bufferedReader().use { it.readText() }

    return mockCategories.split(",").map { it.trim() }
}