package com.nicolas.ecommerce.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel
import com.nicolas.ecommerce.utils.WarningMessage
import com.nicolas.ecommerce.utils.loadSampleCategories
import com.nicolas.ecommerce.utils.loadSampleProducts

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
            CircularProgressIndicator()
        } else {
            if (list.isNullOrEmpty()) {
                WarningMessage(stringResource(R.string.text_list_products_empty_warning_elements_visuals), viewModel)
            } else {
                App(list.orEmpty(), categories.orEmpty())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(productItem: List<Product>, categories: List<String>) {
    val products = remember { productItem }
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.text_title_screen_lobby),
                        modifier = Modifier.fillMaxWidth(),
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
                SearchBar(searchText = searchText, onSearchTextChange = { newText ->
                    searchText = newText
                })

                if (categories.isNotEmpty()) {
                    var itemSelected by remember { mutableStateOf(categories[0]) }
                    CategoriesScreen(
                        categories = categories,
                        onItemSelected = { itemSelected = it }
                    )
                    ProductsColumn(
                        products.filter {
                            it.title.contains(searchText, ignoreCase = true) &&
                                    (it.category.uppercase() == itemSelected.uppercase() || itemSelected.isBlank())
                        }
                    )
                } else {
                    ProductsColumn(
                        products.filter {
                            it.title.contains(searchText, ignoreCase = true)
                        }
                    )
                }
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppPreview() {
    App(loadSampleProducts(), loadSampleCategories())
}
