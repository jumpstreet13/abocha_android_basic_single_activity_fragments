package com.example.cupcake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.screens.CupcakeButton
import com.example.cupcake.screens.CustomRadioButton

@Composable
fun PickerContent(
    products: List<String>,
    selectedProduct: String,
    subtotalPrice: String,
    onRadioButtonClick: (String) -> Unit,
    canselAction: () -> Unit,
    nextAction: () -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item {
            Column {
                products.forEach {
                    val text = it
                    CustomRadioButton(
                        modifier = Modifier,
                        selected = text == selectedProduct,
                        text = text
                    ) {
                        onRadioButtonClick(text)
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .align(Alignment.End),
                    style = MaterialTheme.typography.subtitle1,
                    text = stringResource(R.string.subtotal_price, subtotalPrice)
                )

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CupcakeButton(
                        modifier = Modifier.weight(1F),
                        isWhite = true,
                        text = stringResource(R.string.cancel)
                    ) {
                        canselAction()
                    }
                    CupcakeButton(
                        modifier = Modifier.weight(1F),
                        text = stringResource(R.string.next)
                    ) {
                        nextAction()
                    }
                }
            }
        }
    }
}