package com.example.cupcake.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object Start: Screen()
    @Serializable
    data object Flavor: Screen()
    @Serializable
    data object Pickup: Screen()
    @Serializable
    data object Summary: Screen()
    @Serializable
    data object Custom: Screen()
}

