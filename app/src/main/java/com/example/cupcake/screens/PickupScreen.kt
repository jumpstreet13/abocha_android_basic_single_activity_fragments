package com.example.cupcake.screens

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
import com.example.cupcake.theme.CupcakeAppTheme
import com.example.cupcake.widgets.CupcakeCustomizationStep

@Composable
fun PickupScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navigateToSummary: () -> Unit,
    cancel: () -> Unit,
) {
    val selectedDate = viewModel.date.collectAsState()
    val price = viewModel.price.collectAsState()
    val options = remember { viewModel.dateOptions }

    CupcakeCustomizationStep(
        modifier = modifier,
        currentPrice = stringResource(R.string.subtotal_price, price.value),
        selectedOption = selectedDate.value,
        onOptionSelected = { viewModel.setDate(it) },
        options = options,
        onNext = navigateToSummary,
        onCancel = {
            viewModel.resetOrder()
            cancel()
        }
    )
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun PickupScreenPreview() {
    CupcakeAppTheme {
        var selectedOption by remember { mutableStateOf("Thursday") }
        val options = listOf(
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
        
        CupcakeCustomizationStep(
            currentPrice = stringResource(R.string.subtotal_price, "\$10"),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it },
            options = options,
            onNext = {},
            onCancel = {}
        )
    }
}