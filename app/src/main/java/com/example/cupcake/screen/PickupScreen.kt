package com.example.cupcake.screen

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.design.PageScreen

@Composable
fun PickupScreen(
    viewModel: OrderViewModel,
    onCancel: () -> Unit,
    onNext: () -> Unit
) {
    val dates = remember { viewModel.dateOptions }
    val selectedDay = viewModel.date.observeAsState()
    val subtotalPrice = viewModel.price.observeAsState()
    
    PageScreen(
        items = dates,
        selectedItem = selectedDay,
        subtotalPrice = subtotalPrice,
        onSelect = viewModel::setDate,
        onCancel = onCancel,
        onNext = onNext
    )
}