package com.example.cupcake.compose.ui.designsystem

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cupcake.compose.ui.util.ORDER_CUPCAKE_BUTTON_WIDTH

@Composable
fun CupcakeButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ),
    text: String,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier.widthIn(
            min = ORDER_CUPCAKE_BUTTON_WIDTH
        ),
        colors = colors,
        onClick = { onButtonClick() }
    ) {
        Text(text = text.uppercase())
    }
}

@Composable
fun CupcakeOutlinedButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary
    ),
    text: String,
    onButtonClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .widthIn(
                min = ORDER_CUPCAKE_BUTTON_WIDTH
            ),
        colors = colors,
        onClick = { onButtonClick() }
    ) {
        Text(text = text.uppercase())
    }
}
