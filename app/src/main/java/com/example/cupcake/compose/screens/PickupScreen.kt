package com.example.cupcake.compose.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.cupcake.compose.templates.SelectionTemplate

@Composable
fun PickupScreen(
    dates: List<String>,
    selectedDay: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    SelectionTemplate(
        items = dates,
        selectedItem = selectedDay,
        subtotalPrice = subtotalPrice,
        onSelect = onSelect,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}
