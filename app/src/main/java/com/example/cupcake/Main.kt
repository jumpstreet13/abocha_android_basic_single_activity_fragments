package com.example.cupcake

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel

enum class Screen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

@Composable
fun Main(viewModel: OrderViewModel, navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
            {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
            {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }

        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            enterTransition = enterTransition,
            exitTransition = exitTransition
        ) {


            composable(
                route = Screen.Start.name
            ) {
                StartScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.Flavor.name
            ) {
                FlavorScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.Pickup.name
            ) {
                PickupScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.Summary.name
            ) {
                SummaryScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun AppBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = if (canNavigateBack) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else null
    )
}
