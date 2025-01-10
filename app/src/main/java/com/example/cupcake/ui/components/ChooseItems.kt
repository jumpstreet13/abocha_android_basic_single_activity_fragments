package com.example.cupcake.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ChooseItems(
    list: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.selectableGroup()
    ) {
        list.forEach { text ->
            SelectableItem(
                text = text,
                selectedItem = selectedItem,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Composable
fun SelectableItem(
    text: String,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = text == selectedItem,
                onClick = { onItemSelected(text) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = text == selectedItem, onClick = { onItemSelected(text) })
        Text(text = text, fontSize = 18.sp)
    }
}