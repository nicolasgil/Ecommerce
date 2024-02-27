package com.nicolas.ecommerce.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.R

@Composable
fun ComponentRatingBar(rating: Double) {
    val ratingInt = rating.toInt()
    Text(stringResource(R.string.text_title_rating))
    LinearProgressIndicator(
        progress = rating.toFloat() / 5,
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .background(Color.Blue)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .height(16.dp)
            .background(Color.Gray)
            .widthIn(
                min = 0.dp, max = (ratingInt * 20).dp
            )
    )

}

@Preview(showSystemUi = true)
@Composable
fun PreviewRatingBar() {
    ComponentRatingBar(rating = 2.0)
}