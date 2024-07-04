package com.example.cupcake.navigation

sealed class Screens(val route: String) {
    data object Start : Screens("start_screen")
    data object Flavor : Screens("flavor_screen")
    data object Pickup : Screens("pickup_screen")
    data object Summary : Screens("summary_screen")
}
