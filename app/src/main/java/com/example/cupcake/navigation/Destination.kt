package com.example.cupcake.navigation

sealed class Destination (val route: String) {
    data object Start: Destination("start")
    data object Flavor: Destination("flavor")
    data object Pickup: Destination("pickup")
    data object Summary: Destination("summary")
}