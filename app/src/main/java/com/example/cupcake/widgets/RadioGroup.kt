package com.example.cupcake.widgets

import androidx.compose.runtime.Composable

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    options.forEach { option ->
        RadioVariant(
            label = option,
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) }
        )
    }
}