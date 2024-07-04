package com.example.cupcake.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.Destination
import com.example.cupcake.navigation.getScreenEnterTransition
import com.example.cupcake.navigation.getScreenExitTransition
import com.example.cupcake.presentation.components.AppScreenWrapper
import com.example.cupcake.presentation.components.OrderComponent

fun NavGraphBuilder.addFlavorDestination(
    navHostController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    composable<Destination.FlavorScreen>(
        enterTransition = getScreenEnterTransition(),
        exitTransition = getScreenExitTransition()
    ) {
        val context = LocalContext.current

        FlavorScreen(
            choices = remember {
                listOf(
                    context.getString(R.string.vanilla),
                    context.getString(R.string.chocolate),
                    context.getString(R.string.red_velvet),
                    context.getString(R.string.salted_caramel),
                    context.getString(R.string.coffee)
                )
            },
            selected = sharedViewModel.flavor.observeAsState(context.getString(R.string.vanilla)).value,
            price = sharedViewModel.price.observeAsState().value.orEmpty(),
            onBackButtonClick = navHostController::popBackStack,
            onNextButtonClick = { navHostController.navigate(Destination.PickupScreen) },
            onCancelButtonClick = {
                sharedViewModel.resetOrder()
                navHostController.popBackStack(Destination.StartScreen, false)
            },
            onSelect = { sharedViewModel.setFlavor(it) }
        )
    }
}

@Composable
private fun FlavorScreen(
    choices: List<String>,
    selected: String,
    price: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onSelect: (String) -> Unit
) {
    AppScreenWrapper(
        title = stringResource(id = R.string.order_cupcakes),
        onBackButtonClick = onBackButtonClick
    ) {
        OrderComponent(
            choices = choices,
            selected = selected,
            price = price,
            onNextButtonClick = onNextButtonClick,
            onCancelButtonClick = onCancelButtonClick,
            onSelect = onSelect
        )
    }
}