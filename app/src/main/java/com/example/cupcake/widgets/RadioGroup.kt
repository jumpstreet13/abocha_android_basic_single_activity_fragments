package com.example.cupcake.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    options.forEach { option ->
        RadioVariant(
            modifier = modifier,
            label = option,
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) }
        )
    }
}