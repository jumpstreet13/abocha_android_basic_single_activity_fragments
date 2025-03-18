package com.example.cupcake.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RadioGroupOptions(
    options: List<String>,
    optionSelected: String,
    modifier: Modifier = Modifier,
    onclick: (String) -> Unit = {},
) {
    options.forEach { option ->
        TextRadioButton(
            optionSelected = optionSelected,
            text = option,
            onClick = { onclick.invoke(option) },
            modifier = modifier
        )
    }
}