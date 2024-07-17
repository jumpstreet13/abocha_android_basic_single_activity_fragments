package com.example.cupcake.compose

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

object DestinationMain {
    const val START_ROUTE = "start"
    const val FLAVOR_ROUTE = "flawor"
    const val PICKUP_ROUTE = "pickup"
    const val SUMMARY_ROUTE = "summary"
}

@Composable
fun MainNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel
) {
    NavHost(
        navController = navController,
        startDestination = DestinationMain.START_ROUTE
    ) {
        composable(DestinationMain.START_ROUTE) {
            val defaultFlavor = stringResource(id = R.string.vanilla)
            StartScreen {
                viewModel.setQuantity(it)
                viewModel.setFlavor(defaultFlavor)
                navController.navigate(DestinationMain.FLAVOR_ROUTE)
            }
        }

        composable(DestinationMain.FLAVOR_ROUTE) {
            FlavorScreen(
                price = viewModel.priceF.collectAsState(),
                setFlavor = {
                    viewModel.setFlavor(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(DestinationMain.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(DestinationMain.PICKUP_ROUTE)
                }
            )
        }

        composable(DestinationMain.PICKUP_ROUTE) {
            PickupScreen(
                price = viewModel.priceF.collectAsState(),
                dateOptions = viewModel.dateOptions,
                date = viewModel.dateF.collectAsState(),
                setDate = {
                    viewModel.setDate(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(DestinationMain.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(DestinationMain.SUMMARY_ROUTE)
                }
            )
        }

        composable(DestinationMain.SUMMARY_ROUTE) {
            val context = LocalContext.current
            val orderIntent = orderIntent(viewModel = viewModel)

            SummaryScreen(
                price = viewModel.priceF.collectAsState(),
                quantity = viewModel.quantityF.collectAsState(),
                flavor = viewModel.flavorF.collectAsState(),
                date = viewModel.dateF.collectAsState(),
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(DestinationMain.START_ROUTE, inclusive = false)
                },
                sendOrder = {
                    // Check if there's an app that can handle this intent before launching it
                    if (context.packageManager?.resolveActivity(orderIntent, 0) != null) {
                        // Start a new activity with the given intent (this may open the share dialog on a
                        // device if multiple apps can handle this intent)
                        context.startActivity(orderIntent)
                    }
                }
            )
        }
    }
}

/**
 * Submit the order by sharing out the order details to another app via an implicit intent.
 */
@Composable
fun orderIntent(viewModel: OrderViewModel): Intent {
    // Construct the order summary text with information from the view model
    val numberOfCupcakes = viewModel.quantity.value ?: 0
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(id = R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavorF.collectAsState().value,
        viewModel.dateF.collectAsState().value,
        viewModel.priceF.collectAsState().value.toString()
    )

    // Create an ACTION_SEND implicit intent with order details in the intent extras
    return Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, stringResource(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)
}