package com.nicolas.ecommerce.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.SortBy
import com.nicolas.ecommerce.utils.dummyProducts


@Composable
fun ComponentFilterControls(products: List<Product>, onOrderBySelected: (SortBy) -> Unit) {
    if (products.isNotEmpty()) {
        var sortBy by remember { mutableStateOf(SortBy.RATING_HIGH_TO_LOW) }
        var showDialog by remember { mutableStateOf(false) }

        IconButton(onClick = {
            showDialog = true
        }) {
            Icon(
                imageVector = Icons.Default.FilterAlt,
                contentDescription = stringResource(R.string.text_description_icon_filter)
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                buttons = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        EnumValues<SortBy>().forEach { sortByOption ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                RadioButton(
                                    selected = sortByOption == sortBy,
                                    onClick = { sortBy = sortByOption }
                                )
                                Text(text = sortByOption.displayName)
                            }
                        }
                        Button(
                            modifier = Modifier.padding(4.dp),
                            onClick = {
                                onOrderBySelected(sortBy)
                                showDialog = false
                            }
                        ) {
                            Text(text = stringResource(R.string.text_button_apply_filter))
                        }
                    }
                }
            )
        }
    }
}

@Composable
inline fun <reified T : Enum<T>> EnumValues(): List<T> = enumValues<T>().toList()


@Preview(showSystemUi = true)
@Composable
fun FilterControlsPreview() {
    ComponentFilterControls(dummyProducts(), {})
}