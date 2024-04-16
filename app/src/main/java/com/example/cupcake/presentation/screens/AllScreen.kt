package com.example.cupcake.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AllScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object StartScreen : AllScreen(
        route = "start_screen",
        title = "Start",
        icon = Icons.Default.Person
    )

    data object FlavorScreen : AllScreen(
        route = "flavor_screen",
        title = "flavor",
        icon = Icons.Default.Menu
    )

    data object PickupScreen : AllScreen(
        route = "Pickup_screen",
        title = "Pickup",
        icon = Icons.Default.Home
    )

    data object SummaryScreen : AllScreen(
        route = "Summary_screen",
        title = "Summary",
        icon = Icons.Default.Home
    )
}
