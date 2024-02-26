package com.nicolas.ecommerce.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel
import com.nicolas.ecommerce.utils.toProduct

@Composable
fun LobbyScreen(viewModel: LobbyViewModel) {

    val list by viewModel.list.observeAsState()
    val loading by viewModel.loading.observeAsState()
    val categories by viewModel.categories.observeAsState()



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (loading == true) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

        if (!list.isNullOrEmpty()) {
            if (!categories.isNullOrEmpty()) {

                App(list!!, categories!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(productItem: List<Product>, categories: List<String>) {

    var searchText by remember { mutableStateOf("") }

    val products = remember {
        productItem
    }


    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lobby_text_title),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
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

                CategoriesScreen(categories = categories)


                ProductsColumn(
                    products.filter {
                        it.title.contains(searchText, ignoreCase = true)
                    }
                )
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppPreview() {
    val jsonProduct = LocalContext.current.resources.openRawResource(R.raw.mock_product)
        .bufferedReader().use { it.readText() }

    val sampleProducts = listOf(jsonProduct.toProduct())
    App(sampleProducts, listOf("Categoría 1", "Categoría 2", "Categoría 3"))
}