package com.example.cupcake

import StartScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.Screens
import com.example.cupcake.ui.theme.CupcakeTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun CupcakeApp(sendOrder: (String) -> Unit) {
    CupcakeTheme {
        val navController = rememberNavController()
        val viewModel: OrderViewModel = viewModel()
        val selectedFlavor by viewModel.flavor.observeAsState("")
        val price by viewModel.price.observeAsState("")
        val selectedDate by viewModel.date.observeAsState("")
        val dateOptions = viewModel.dateOptions
        val quantity by viewModel.quantity.observeAsState()
        val flavor by viewModel.flavor.observeAsState("")
        val date by viewModel.date.observeAsState("")
        val numberOfCupcakes = viewModel.quantity.value ?: 0
        val orderSummary = stringResource(
            R.string.order_details,
            pluralStringResource(
                R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes
            ),
            viewModel.flavor.value.toString(),
            viewModel.date.value.toString(),
            viewModel.price.value.toString()
        )
        val vanilla = stringResource(id = R.string.vanilla)

        NavHost(navController = navController, startDestination = Screens.Start.route) {
            composable(Screens.Start.route) {
                StartScreen(
                    onClick = { quantity ->
                        viewModel.setQuantity(quantity)
                        if (viewModel.hasNoFlavorSet()) {
                            viewModel.setFlavor(vanilla)
                        }
                        navController.navigate(Screens.Flavor.route)
                    }
                )
            }
            composable(Screens.Flavor.route) {
                FlavorScreen(
                    onRadioButtonClicked = { flavor -> viewModel.setFlavor(flavor) },
                    onNextClick = { navController.navigate(Screens.Pickup.route) },
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(Screens.Start.route, false)
                    },
                    onBackClick = { navController.popBackStack() },
                    selectedFlavor = selectedFlavor,
                    price = price
                )
            }
            composable(Screens.Pickup.route) {
                PickupScreen(
                    onRadioButtonClicked = { date -> viewModel.setDate(date)  },
                    onNextClick = { navController.navigate(Screens.Summary.route) },
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(Screens.Start.route, false)
                    },
                    onBackClick = { navController.popBackStack() },
                    selectedDate = selectedDate,
                    dateOptions = dateOptions.toImmutableList(),
                    price = price
                )
            }
            composable(Screens.Summary.route) {
                SummaryScreen(
                    onSendOrder = sendOrder,
                    onCancelOrder = {
                        viewModel.resetOrder()
                        navController.popBackStack(Screens.Start.route, false)
                    },
                    onBackClick = { navController.popBackStack() },
                    orderSummary = orderSummary,
                    quantity = quantity,
                    flavor = flavor,
                    date = date,
                    price = price,
                )
            }
        }
    }
}
