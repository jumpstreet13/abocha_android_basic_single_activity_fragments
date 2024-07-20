package com.example.cupcake.compose.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.compose.templates.SelectionTemplate

@Composable
fun FlavorScreen(
    selectedFlavor: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    SelectionTemplate(
        items = getFlavors(),
        selectedItem = selectedFlavor,
        subtotalPrice = subtotalPrice,
        onSelect = onSelect,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}

@Composable
@NonRestartableComposable
fun getFlavors(): List<String> {
    return listOf(
        stringResource(id = R.string.vanilla),
        stringResource(id = R.string.chocolate),
        stringResource(id = R.string.red_velvet),
        stringResource(id = R.string.salted_caramel),
        stringResource(id = R.string.coffee)
    )
}
