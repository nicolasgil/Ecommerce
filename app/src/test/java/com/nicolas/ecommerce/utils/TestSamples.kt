package com.nicolas.ecommerce.utils

import com.nicolas.ecommerce.domain.models.Product

val sampleTestProduct =
    Product(
        id = 0,
        title = "iPhone X",
        description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
        price = 899.0,
        discountPercentage = 17.98,
        rating = 4.4,
        brand = "Apple",
        stock = 34,
        category = "smartphones",
        thumbnail = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/2/1.jpg",
            "https://cdn.dummyjson.com/product-images/2/2.jpg",
            "https://cdn.dummyjson.com/product-images/2/3.jpg",
            "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg"
        ),
    )

val sampleTestCategories = listOf("Categoría 1", "Categoría 2", "Categoría 3")
