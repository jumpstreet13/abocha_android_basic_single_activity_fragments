package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(val navHostController: NavHostController) {
    fun navigateTo(route: String, needPopUp: Boolean = false) {
        navHostController.navigate(route) {
            if (route == Screen.Start.route || needPopUp) {
                popUpTo(route)
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}