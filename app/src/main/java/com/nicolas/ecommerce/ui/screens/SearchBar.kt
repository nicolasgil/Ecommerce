package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            placeholder = { Text("Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Icon(
            Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier
                .size(54.dp)
                .padding(end = 15.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(searchText = "Buscar", onSearchTextChange = {})
}