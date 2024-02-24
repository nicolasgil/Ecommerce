package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.domain.Product

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
    val sampleProducts = listOf(
        Product(
            2, "iPhone 9",
            "An apple mobile which is nothing like apple, An apple mobile which is nothing like apple, An apple mobile which is nothing like apple",
            549.0,
            12.96,
            4.69,
            94,
            "Apple",
            "smartphones",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            arrayListOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
        )
    )
    ProductsColumn(products = sampleProducts)
}