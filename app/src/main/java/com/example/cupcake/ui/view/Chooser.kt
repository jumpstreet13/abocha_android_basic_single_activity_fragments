package com.example.cupcake.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Chooser(
    elements:List<String>,
    selectedElement: String,
    onElementSelect: (element: String) -> Unit,
) {
    Column(Modifier.selectableGroup()) {
        elements.forEach { element ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = element == selectedElement,
                    onClick = { onElementSelect.invoke(element) }
                )
                Text(text = element)
            }
        }
    }
}
