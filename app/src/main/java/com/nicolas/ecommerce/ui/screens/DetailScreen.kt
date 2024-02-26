package com.nicolas.ecommerce.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.utils.UnexpectedError
import com.nicolas.ecommerce.utils.loadSampleNavController
import com.nicolas.ecommerce.utils.loadSampleProducts
import kotlinx.coroutines.delay

@Composable
fun DetailScreen(navController: NavHostController, product: Product?) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(product) {
        delay(1000)
        isLoading = false
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        } else {
            if (product != null) {
                DetailContent(product = product, navController)
            } else {
                UnexpectedError()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(product: Product, navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Detalles del producto")
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }, content = { padding ->
        Card(
            modifier = Modifier
                .padding(4.dp)
                .background(Color.White),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    ImageSlider(images = product.images)

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = product.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(rating = product.rating)
                        Text(
                            text = "Stock: ${product.stock}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    PriceSection(product = product)

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ButtonCustom("Modificar", {}, Color.Blue)
                        ButtonCustom("Borrar", {}, Color.Red)

                    }
                }
            }
        }
    })

}

@Composable
fun ButtonCustom(message: String, onClick: () -> Unit, colorForeground: Color) {
    Button(
        onClick = { onClick },
        modifier = Modifier
            .width(150.dp)
            .height(40.dp)
            .background(Color.White),
        colors = ButtonColors(
            containerColor = colorForeground,
            contentColor = Color.White,
            disabledContainerColor = colorForeground,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = message, style = TextStyle(
                color = Color.White
            )
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewDetail() {
    DetailContent(loadSampleProducts().first(), loadSampleNavController())

}