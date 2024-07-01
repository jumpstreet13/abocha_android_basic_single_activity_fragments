package com.example.cupcake.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.AppNavHost

@Composable
fun MainScreen(viewModel: OrderViewModel) {
    AppNavHost(navController = rememberNavController(), viewModel = viewModel)
}
