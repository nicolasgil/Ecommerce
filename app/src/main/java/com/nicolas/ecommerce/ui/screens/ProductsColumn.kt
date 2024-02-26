package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.loadSampleProducts

@Composable
fun ProductsColumn(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsColumnPreview() {

    ProductsColumn(products = loadSampleProducts())
}