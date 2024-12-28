package com.example.cupcake.screen

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.design.PageScreen

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel,
    onCancel: () -> Unit,
    onNext: () -> Unit
) {
    val selectedFlavor = viewModel.flavor.observeAsState()
    val subtotalPrice = viewModel.price.observeAsState()

    PageScreen(
        items = getFlavors(),
        selectedItem = selectedFlavor,
        subtotalPrice = subtotalPrice,
        onSelect = viewModel::setFlavor,
        onCancel = onCancel,
        onNext = onNext
    )
}

@Composable
@NonRestartableComposable
private fun getFlavors(): List<String> {
    return listOf(
        stringResource(id = R.string.vanilla),
        stringResource(id = R.string.chocolate),
        stringResource(id = R.string.red_velvet),
        stringResource(id = R.string.salted_caramel),
        stringResource(id = R.string.coffee)
    )
}
