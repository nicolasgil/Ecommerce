package com.nicolas.ecommerce.ui.screens.commons


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage


@Composable
fun LoadImageFromUrl(
    imageUrl: String,
    description: String,
    placeholder: Int,
    width: Dp,
    height: Dp
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = description,
        placeholder = painterResource(id = placeholder),
        modifier = Modifier
            .size(width, height)
            .fillMaxWidth(),
        alignment = Alignment.Center
    )
}