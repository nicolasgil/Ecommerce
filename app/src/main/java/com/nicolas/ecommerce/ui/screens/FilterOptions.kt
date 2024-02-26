package com.nicolas.ecommerce.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


data class FilterOptions(
    val category: String = "categories[0]",
    val minPrice: Int = 0,
    val maxPrice: Int = 100,
    val minDiscount: Double = 0.0,
    val maxDiscount: Double = 100.0,
    val minRating: Double = 0.0,
    val maxRating: Double = 5.0,
    val minStock: Int = 0,
    val maxStock: Int = Int.MAX_VALUE,
    val brand: String = "",
    val sortBy: SortBy = SortBy.NONE
)

enum class SortBy {
    NONE,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    DISCOUNT_HIGH_TO_LOW,
    RATING_HIGH_TO_LOW
}

@Preview
@Composable
fun FilterOptionsPreview() {
    val sampleFilterOptions = FilterOptions()
    Text("Filter Options Preview: $sampleFilterOptions")
}
