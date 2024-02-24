package com.nicolas.ecommerce.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nicolas.ecommerce.domain.Product
import com.nicolas.ecommerce.ui.screens.commons.categories
import com.nicolas.ecommerce.ui.theme.EcommerceTheme

@Composable
fun LobbyScreen() {
    EcommerceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var searchText by remember { mutableStateOf("") }

    val products = remember {
        listOf(
            Product(
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
            ),
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
    }

    val selectedCategory by remember { mutableStateOf(categories[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                SearchBar(
                    searchText = searchText,
                    onSearchTextChange = { newText ->
                        searchText = newText
                    }
                )


                ProductsColumn(
                    products.filter {
                        it.title.contains(searchText, ignoreCase = true) &&
                                (it.category == selectedCategory || selectedCategory == categories[0])
                    }
                )
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppPreview() {
    App()
}