package com.nicolas.ecommerce.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterControls(filterOptions: FilterOptions, onFilterOptionsChanged: (FilterOptions) -> Unit) {

}

@Preview
@Composable
fun FilterControlsPreview() {
    val sampleFilterOptions = FilterOptions()
    FilterControls(filterOptions = sampleFilterOptions, onFilterOptionsChanged = {})
}