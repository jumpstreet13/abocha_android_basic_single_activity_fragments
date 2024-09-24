package com.example.cupcake.screen


import android.content.Intent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: OrderViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Purpose.START_ROUTE
    ) {
        addComposableWithAnimations(Purpose.START_ROUTE) {
            val defaultFlavor = stringResource(id = R.string.vanilla)

            StartScreen {
                if (viewModel.hasNoFlavorSet()) {
                    viewModel.setFlavor(defaultFlavor)
                }
                viewModel.setQuantity(it)
                navController.navigate(Purpose.FLAVOR_ROUTE)
            }
        }


        addComposableWithAnimations(Purpose.FLAVOR_ROUTE) {
            TasteScreen(
                price = viewModel.price.collectAsState(),
                setFlavor = {
                    viewModel.setFlavor(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(Purpose.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(Purpose.PICKUP_ROUTE)
                }
            )
        }


        addComposableWithAnimations(Purpose.PICKUP_ROUTE) {
            TuckUpScreen(
                price = viewModel.price.collectAsState(),
                dateOptions = viewModel.dateOptions,
                date = viewModel.date.collectAsState(),
                setDate = {
                    viewModel.setDate(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(Purpose.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(Purpose.SUMMARY_ROUTE)
                }
            )
        }


        addComposableWithAnimations(Purpose.SUMMARY_ROUTE) {
            val context = LocalContext.current
            val orderIntent = orderIntent(viewModel = viewModel)


            ArgumentScreen(
                price = viewModel.price.collectAsState(),
                quantity = viewModel.quantity.collectAsState(),
                flavor = viewModel.flavor.collectAsState(),
                date = viewModel.date.collectAsState(),
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(Purpose.START_ROUTE, inclusive = false)
                },
                sendOrder = {
                    if (context.packageManager?.resolveActivity(orderIntent, 0) != null) {
                        context.startActivity(orderIntent)
                    }
                }
            )
        }
    }
}


fun NavGraphBuilder.addComposableWithAnimations(
    route: String,
    content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = route,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 1000)
            ) + fadeIn()
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 1000)
            ) + fadeOut()
        },
        popEnterTransition = {
            slideInVertically(
                initialOffsetY = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 1000)
            ) + fadeIn()
        },
        popExitTransition = {
            slideOutVertically(
                targetOffsetY = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 1000)
            ) + fadeOut()
        },
        content = content
    )
}

@Composable
fun orderIntent(viewModel: OrderViewModel): Intent {
    // Construct the order summary text with information from the view model
    val numberOfCupcakes = viewModel.quantity.collectAsState().value
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(id = R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavor.collectAsState().value,
        viewModel.date.collectAsState().value,
        viewModel.price.collectAsState().value.toString()
    )
    return Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, stringResource(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)
}

object Purpose {
    const val START_ROUTE = "start"
    const val FLAVOR_ROUTE = "flavor"
    const val PICKUP_ROUTE = "pickup"
    const val SUMMARY_ROUTE = "summary"
}