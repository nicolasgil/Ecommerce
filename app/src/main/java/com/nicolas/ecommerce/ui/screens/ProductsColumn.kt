package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.screens.commons.toProduct

@Composable
fun ProductsColumn(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsColumnPreview() {
    val jsonString = LocalContext.current.resources.openRawResource(R.raw.mock_product)
        .bufferedReader().use { it.readText() }

    val sampleProducts = listOf(jsonString.toProduct())
    ProductsColumn(products = sampleProducts)
}