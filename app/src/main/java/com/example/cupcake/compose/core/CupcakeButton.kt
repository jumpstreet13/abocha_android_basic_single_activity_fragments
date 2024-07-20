package com.example.cupcake.compose.core

import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.cupcake.R

@Composable
fun CupcakeButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = colorResource(id = R.color.white)
    ),
    text: String,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        onClick = { onButtonClick() }
    ) {
        Text(text = text.uppercase())
    }
}

@Composable
fun OutlinedCupcakeButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = MaterialTheme.colors.primary
    ),
    text: String,
    onButtonClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        colors = colors,
        onClick = { onButtonClick() }
    ) {
        Text(text = text.uppercase())
    }
}
