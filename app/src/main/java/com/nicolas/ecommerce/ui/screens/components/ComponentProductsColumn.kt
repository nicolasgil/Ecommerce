package com.nicolas.ecommerce.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.dummyNavController
import com.nicolas.ecommerce.utils.dummyProducts

@Composable
fun ComponentProductsColumn(products: List<Product>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(products) { product ->
            ComponentProductCard(product = product, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsColumnPreview() {
    ComponentProductsColumn(products = dummyProducts(), dummyNavController())
}