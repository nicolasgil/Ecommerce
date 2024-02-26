package com.nicolas.ecommerce.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.utils.loadSampleCategories


@Composable
fun CategoriesScreen(categories: List<String>, onItemSelected: (String) -> Unit) {
    var selectedCategory by remember { mutableStateOf(categories[0].uppercase()) }
    var expaned by remember { mutableStateOf(false) }

    Column(modifier = Modifier.background(Color.White)) {
        OutlinedTextField(
            value = selectedCategory,
            enabled = false,
            readOnly = true,
            onValueChange = { selectedCategory = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { expaned = true }
                .border(1.dp, Color.Black, ShapeDefaults.Small),
            trailingIcon = {
                Icon(
                    tint = Color.Black,
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.text_description_icon_arrow_down_categories),
                    modifier = Modifier.size(24.dp)
                )
            },
            textStyle = TextStyle.Default.copy(color = Color.Black),

            )

        DropdownMenu(
            expanded = expaned,
            onDismissRequest = { expaned = false },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = category.uppercase()
                        expaned = false
                        onItemSelected(selectedCategory.uppercase())
                    },
                    text = { Text(text = category.uppercase()) }
                )
            }
        }

    }


}

@Composable
@Preview(showBackground = true)
fun CategoriesListPreview() {
    CategoriesScreen(
        categories = loadSampleCategories(),
        onItemSelected = {})
}
