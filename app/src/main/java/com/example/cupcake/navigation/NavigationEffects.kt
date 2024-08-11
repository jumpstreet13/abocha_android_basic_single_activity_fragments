package com.example.cupcake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow

@Composable
fun NavigationEffects(
    navigationFlow: Flow<NavigationIntent>,
    navHostController: NavHostController
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, navigationFlow, navHostController) {
        navigationFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { intent ->
                when (intent) {
                    is NavigationIntent.NavigateBack -> {
                        if (intent.route != null) {
                            navHostController.popBackStack(intent.route, intent.inclusive)
                        } else {
                            navHostController.popBackStack()
                        }
                    }
                    is NavigationIntent.NavigateTo -> {
                        navHostController.navigate(intent.route) {
                            launchSingleTop = intent.isSingleTop
                            intent.popUpToRoute?.let { popToRoute ->
                                popUpTo(popToRoute) {
                                    inclusive = intent.inclusive
                                }
                            }
                        }
                    }
                }
        }
    }
}