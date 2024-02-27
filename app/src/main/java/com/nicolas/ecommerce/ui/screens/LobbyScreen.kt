package com.nicolas.ecommerce.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel
import com.nicolas.ecommerce.utils.SortBy
import com.nicolas.ecommerce.utils.WarningMessage
import com.nicolas.ecommerce.utils.applySorting
import com.nicolas.ecommerce.utils.dummyCategories
import com.nicolas.ecommerce.utils.dummyNavController
import com.nicolas.ecommerce.utils.dummyProducts

@Composable
fun LobbyScreen(viewModel: LobbyViewModel, navController: NavController) {
    val list by viewModel.list.observeAsState()
    val loading by viewModel.loading.observeAsState()
    val categories by viewModel.categories.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (loading == true) {
            CircularProgressIndicator()
        } else {
            if (list.isNullOrEmpty()) {
                WarningMessage(stringResource(R.string.text_list_products_empty_warning_elements_visuals), viewModel)
            } else {
                PrincipalScreen(list.orEmpty(), categories.orEmpty(), navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    productItem: List<Product>,
    listCategories: List<String>,
    navController: NavController
) {
    var searchText by remember { mutableStateOf("") }
    val originalList by remember { mutableStateOf(productItem) }
    var currentList by remember { mutableStateOf(originalList) }
    var orderBy by remember { mutableStateOf(SortBy.RATING_HIGH_TO_LOW) }

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.text_title_screen_lobby),
                            textAlign = TextAlign.Center
                        )

                        FilterControls(originalList, onOrderBySelected = {
                            orderBy = it
                            currentList = applySorting(originalList, orderBy)
                        })
                    }
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
                    currentList = applyFilterAndOrder(originalList, searchText, orderBy)
                })

                if (listCategories.isNotEmpty()) {
                    var itemSelected by remember { mutableStateOf(listCategories[0]) }

                    CategoriesScreen(listCategories,
                        onItemSelected = { itemSelected = it })

                    currentList =
                        applyFilterAndOrder(originalList, searchText, orderBy, itemSelected)

                } else {
                    currentList = applyFilterAndOrder(originalList, searchText, orderBy)
                }

                ProductsColumn(currentList, navController)

            }
        }
    )
}

fun applyFilterAndOrder(
    list: List<Product>,
    searchText: String,
    orderBy: SortBy,
    category: String = ""
): List<Product> {
    return list.filter {
        it.title.contains(searchText, ignoreCase = true) &&
                (category.isBlank() || it.category.equals(category, ignoreCase = true))
    }.let { applySorting(it, orderBy) }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppPreview() {
    PrincipalScreen(
        dummyProducts(), dummyCategories(), dummyNavController()
    )
}
