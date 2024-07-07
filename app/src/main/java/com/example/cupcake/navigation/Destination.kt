package com.example.cupcake.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {
    @Serializable
    data object StartScreen : Destination()

    @Serializable
    data object FlavorScreen : Destination()

    @Serializable
    data object PickupScreen : Destination()

    @Serializable
    data object SummaryScreen : Destination()
}