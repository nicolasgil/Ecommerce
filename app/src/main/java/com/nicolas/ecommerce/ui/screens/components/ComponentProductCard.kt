package com.nicolas.ecommerce.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.navigation.AppScreen
import com.nicolas.ecommerce.utils.dummyNavController
import com.nicolas.ecommerce.utils.dummyProducts

@Composable
fun ComponentProductCard(product: Product, navController: NavController) {
    var isDialogVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.White)
            .clickable {
                if (product.stock > 0) {
                    navController.navigate(AppScreen.DetailScreen.route + "/${product.id}")
                } else {
                    isDialogVisible = true
                }
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                LoadImageFromUrl(
                    imageUrl = product.thumbnail,
                    description = stringResource(R.string.text_description_image_product_card),
                    R.drawable.ic_placeholder_image
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
                        stringResource(R.string.text_symbol_money) + product.price.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Blue)
                    )
                    Rating(product.rating)
                }
            }
        }
    }

    if (isDialogVisible) {
        OutOfStockDialog(
            onDismiss = {
                isDialogVisible = false
            }
        )
    }
}


@Composable
fun Rating(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.BarChart,
            contentDescription = stringResource(R.string.text_icon_rating_product_card),
            tint = Color(0xFFF39C12)
        )
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
    ComponentProductCard(product = dummyProducts().first(), dummyNavController())
}