package com.nicolas.ecommerce.ui.screens

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.domain.Product
import com.nicolas.ecommerce.ui.screens.commons.LoadImageFromUrl

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { /* Handle click */ },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            LoadImageFromUrl(
                imageUrl = product.thumbnail,
                description = "Product",
                R.drawable.arrow_down_float,
                500.dp, 150.dp
            )
            Text(
                product.title,
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Black)
            )
            Text(
                product.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    product.price.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Blue)
                )
                Rating(product.rating)
            }
        }
    }
}


@Composable
fun Rating(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = String.format("%.1f", rating),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFF39C12)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val sampleProduct = Product(
        1, "iPhone 9",
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

    ProductCard(product = sampleProduct)
}