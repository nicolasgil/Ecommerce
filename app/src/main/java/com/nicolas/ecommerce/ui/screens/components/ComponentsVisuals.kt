package com.nicolas.ecommerce.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nicolas.ecommerce.R
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel

@Composable
fun WarningMessage(message: String?, viewModel: LobbyViewModel?) {
    if (viewModel != null && message != null) {
        val loading by viewModel.loading.observeAsState()
        if (loading == true) {
            CircularProgressIndicator()
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = stringResource(R.string.text_description_icon_warning_lobby),
                            tint = Color.Blue,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = message,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = { viewModel.tryAgainGetProducts() },
                            colors = ButtonColors(
                                containerColor = Color.Blue,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Blue,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(text = stringResource(R.string.text_button_retry_elements_visuals))
                        }
                    }
                }
            }
        }
    } else {
        UnexpectedError()
    }
}

@Composable
fun UnexpectedError() {
    var isAlertDialogVisible by remember { mutableStateOf(true) }

    if (isAlertDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isAlertDialogVisible = false
            },
            title = {
                Text(text = stringResource(R.string.text_title_button_unexpected_error_elements_visuals))
            },
            text = {
                Text(text = stringResource(R.string.text_body_button_unexpected_error_elements_visuals))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isAlertDialogVisible = false
                    }
                ) {
                    Text(text = stringResource(R.string.text_button_dialog_out_stock))
                }
            }
        )
    }
}

@Composable
fun LoadImageFromUrl(
    imageUrl: String,
    description: String,
    placeholder: Int,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = description,
        contentScale = ContentScale.Fit,
        placeholder = painterResource(id = placeholder),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        alignment = Alignment.Center
    )
}


@Composable
fun OutOfStockDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(R.string.text_title_dialog_out_stock))
        },
        text = {
            Text(text = stringResource(R.string.text_body_dialog_out_stock))
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = stringResource(R.string.text_button_dialog_out_stock))
            }
        }
    )
}

@Composable
@Preview(showSystemUi = true)
fun PreviewOutOfStockDialog() {
    OutOfStockDialog(onDismiss = {})
}
