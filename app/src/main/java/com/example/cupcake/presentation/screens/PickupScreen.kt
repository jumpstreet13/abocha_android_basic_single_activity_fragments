package com.example.cupcake.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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

fun NavGraphBuilder.addPickupDestination(
    navHostController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    composable<Destination.PickupScreen>(
        enterTransition = getScreenEnterTransition(),
        exitTransition = getScreenExitTransition()
    ) {
        PickupScreen(
            choices = sharedViewModel.dateOptions,
            selected = sharedViewModel.date.observeAsState().value.orEmpty(),
            price = sharedViewModel.price.observeAsState().value.orEmpty(),
            onBackButtonClick = navHostController::popBackStack,
            onNextButtonClick = { navHostController.navigate(Destination.SummaryScreen) },
            onCancelButtonClick = {
                sharedViewModel.resetOrder()
                navHostController.popBackStack(Destination.StartScreen, false)
            },
            onSelect = { sharedViewModel.setDate(it) }
        )
    }
}

@Composable
private fun PickupScreen(
    choices: List<String>,
    selected: String,
    price: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onSelect: (String) -> Unit
) {
    AppScreenWrapper(
        title = stringResource(id = R.string.choose_pickup_date),
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