package com.example.cupcake.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.cupcake.*
import com.example.cupcake.model.OrderViewModel

@Composable
fun PickupScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {
    val additionalProducts = viewModel.dateOptions
    val selectedDate = viewModel.date.observeAsState().value
    val subtotalPrice = viewModel.price.observeAsState().value

    PickerContent(
        products = additionalProducts,
        selectedProduct = selectedDate ?: "",
        subtotalPrice = subtotalPrice ?: "",
        onRadioButtonClick = { viewModel.setDate(it) },
        canselAction = {
            viewModel.resetOrder()
            navController.popBackStack(Start, false)
        },
        nextAction = {
            navController.navigate(Summary)
        }
    )
}