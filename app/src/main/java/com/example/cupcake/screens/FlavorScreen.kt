package com.example.cupcake.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.widgets.CupcakeCustomizationStep

@Composable
fun FlavorScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navigateToPickup: () -> Unit,
    cancel: () -> Unit,
) {
    val price = viewModel.price.collectAsState()
    val selectedFlavor = viewModel.flavor.collectAsState()

    CupcakeCustomizationStep(
        modifier = modifier,
        currentPrice = stringResource(R.string.subtotal_price, "\$${price}"),
        selectedOption = selectedFlavor.value,
        onOptionSelected = { viewModel.setFlavor(it) },
        options = listOf(
            stringResource(R.string.vanilla),
            stringResource(R.string.chocolate),
            stringResource(R.string.red_velvet),
            stringResource(R.string.salted_caramel),
            stringResource(R.string.coffee)
        ),
        onNext = navigateToPickup,
        onCancel = {
            viewModel.resetOrder()
            cancel()
        },
    )
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun FlavorScreenPreview() {
    MaterialTheme {
        val vanillaFlavor = stringResource(R.string.vanilla)
        var selectedFlavor by remember { mutableStateOf(vanillaFlavor) }

        CupcakeCustomizationStep(
            currentPrice = stringResource(R.string.subtotal_price, "$10"),
            selectedOption = selectedFlavor,
            onOptionSelected = { selectedFlavor = it },
            options = listOf(
                stringResource(R.string.vanilla),
                stringResource(R.string.chocolate),
                stringResource(R.string.red_velvet),
                stringResource(R.string.salted_caramel),
                stringResource(R.string.coffee)
            ),
            onNext = {},
            onCancel = {},
        )
    }
}