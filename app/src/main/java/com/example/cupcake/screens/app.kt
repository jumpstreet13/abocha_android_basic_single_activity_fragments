package com.example.cupcake.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.theme.MainTheme


val LocalNavigation = compositionLocalOf<NavHostController> {
    error("No navigation in composition.")
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    data object Home : Screen("home", R.string.app_name)
    data object Flavor : Screen("flavor", R.string.choose_flavor)
    data object Pickup : Screen("pickup", R.string.choose_pickup_date)
    data object Summary : Screen("summary", R.string.order_summary)
}

@Composable
fun App() {
    MainTheme {
        CompositionLocalProvider(
            LocalNavigation provides rememberNavController()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AppBar(modifier = Modifier.fillMaxWidth())
                AppContent(modifier = Modifier.fillMaxWidth().weight(1f))
            }
        }
    }
}

@Composable
private fun AppBar(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val navEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navEntry?.destination?.route
    val icon = @Composable {
        IconButton(
            onClick = navController::popBackStack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
    TopAppBar(
        modifier = Modifier.then(modifier),
        navigationIcon = currentRoute.takeIf {
            it != Screen.Home.route
        }?.let {
            icon
        },
        title = {
            Text(
                text = stringResource(
                    when (currentRoute) {
                        Screen.Flavor.route -> Screen.Flavor.resourceId
                        Screen.Pickup.route -> Screen.Pickup.resourceId
                        Screen.Summary.route -> Screen.Summary.resourceId
                        else -> Screen.Home.resourceId
                    }
                )
            )
        }
    )
}

@Composable
private fun AppContent(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val duration = 350
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.then(modifier),
        enterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = duration)
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = duration)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = duration)
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = duration)
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = duration)
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = duration)
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = duration)
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = duration)
            )
        }
    ) {
        composable(route = Screen.Home.route) {
            StartScreen()
        }
        composable(route = Screen.Flavor.route) {
            FlavorScreen()
        }
        composable(route = Screen.Pickup.route) {
            PickupScreen()
        }
        composable(route = Screen.Summary.route) {
            SummaryScreen()
        }
    }
}
