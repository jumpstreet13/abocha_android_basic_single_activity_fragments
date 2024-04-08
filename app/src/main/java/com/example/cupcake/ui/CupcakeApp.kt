package com.example.cupcake.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

enum class Screen {

    START,

    FLAVOR,

    DATE,

    SUMMARY,
}

@Composable
fun CupcakeApp(
    navController: NavHostController,
    viewModel: OrderViewModel,
    shareAction: (text: String) -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.START.name) {
        composable(route = Screen.START.name) {
            val defaultFlavor = stringResource(R.string.vanilla)
            StartScreen(
                onCupcakesSelect = { numberCupcakes ->
                    if (viewModel.hasNoFlavorSet()) {
                        viewModel.setFlavor(defaultFlavor)
                    }
                    viewModel.setQuantity(numberCupcakes)
                    navController.navigate(Screen.FLAVOR.name)
                },
            )
        }

        composable(
            route = Screen.FLAVOR.name,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            val flavor by viewModel.flavor.observeAsState()
            val price by viewModel.price.observeAsState()
            val flavors = listOf(
                stringResource(R.string.vanilla),
                stringResource(R.string.chocolate),
                stringResource(R.string.red_velvet),
                stringResource(R.string.salted_caramel),
                stringResource(R.string.coffee),
            )
            ChooseScreen(
                title = stringResource(id = R.string.choose_flavor),
                elements = flavors,
                selectedElement = flavor.orEmpty(),
                price = price.orEmpty(),
                onElementClick = { viewModel.setFlavor(it) },
                onBackButtonClick = { navController.popBackStack() },
                onCancelClick = cancelOrder(viewModel, navController),
                onNextClick = { navController.navigate(Screen.DATE.name) },
            )
        }

        composable(
            route = Screen.DATE.name,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            val date by viewModel.date.observeAsState()
            val price by viewModel.price.observeAsState()
            ChooseScreen(
                title = stringResource(id = R.string.pickup_date),
                elements = viewModel.dateOptions,
                selectedElement = date.orEmpty(),
                price = price.orEmpty(),
                onElementClick = { viewModel.setDate(it) },
                onBackButtonClick = { navController.popBackStack() },
                onCancelClick = cancelOrder(viewModel, navController),
                onNextClick = { navController.navigate(Screen.SUMMARY.name) },
            )
        }

        composable(
            route = Screen.SUMMARY.name,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            val quantity by viewModel.quantity.observeAsState()
            val flavor by viewModel.flavor.observeAsState()
            val date by viewModel.date.observeAsState()
            val price by viewModel.price.observeAsState()
            SummaryScreen(
                quantity = quantity ?: 0,
                flavor = flavor.orEmpty(),
                date = date.orEmpty(),
                price = price.orEmpty(),
                onBackButtonClick = { navController.popBackStack() },
                onCancelClick = cancelOrder(viewModel, navController),
                onSendClick = { shareAction(it) },
            )
        }
    }
}

@Composable
private fun cancelOrder(
    viewModel: OrderViewModel,
    navController: NavHostController
): () -> Unit = {
    viewModel.resetOrder()
    navController.popBackStack(Screen.START.name, inclusive = false)
}