package com.example.cupcake.design

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cupcake.util.MIN_BUTTON_WIDTH

@Composable
fun Button(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ),
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.widthIn(
            min = MIN_BUTTON_WIDTH
        ),
        colors = colors,
        onClick = onClick
    ) {
        Text(text = text.uppercase())
    }
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary
    ),
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .widthIn(
                min = MIN_BUTTON_WIDTH
            ),
        colors = colors,
        onClick = onClick
    ) {
        Text(text = text.uppercase())
    }
}