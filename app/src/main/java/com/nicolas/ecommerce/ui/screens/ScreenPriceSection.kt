package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.loadSampleProducts
import kotlin.math.roundToInt

@Composable
fun PriceSection(product: Product) {
    val discountPrice = product.price * (1 - product.discountPercentage / 100)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.text_title_price_section_price),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.text_title_discount_section_price),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.text_title_final_price_section_price),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
            )
            Text(
                text = "-${product.discountPercentage}%",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )
            Text(
                text = "$${discountPrice.roundToInt()}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSectionPrice() {
    PriceSection(loadSampleProducts().first())
}