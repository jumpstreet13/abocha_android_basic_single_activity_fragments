package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object DestinationMain {
    const val START_ROUTE = "start"
}

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = DestinationMain.START_ROUTE
    ) {
        composable(DestinationMain.START_ROUTE) {
            StartScreen()
        }
    }
}