package com.nicolas.ecommerce.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
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
fun loadSampleCategories(): List<String> {
    val mockCategories = LocalContext.current.resources.openRawResource(R.raw.mock_categories)
        .bufferedReader().use { it.readText() }

    return mockCategories.split(",").map { it.trim() }
}