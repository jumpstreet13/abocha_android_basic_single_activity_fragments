package com.example.cupcake.navigation

import kotlinx.serialization.Serializable

@Suppress("ConvertObjectToDataObject")
sealed interface Destination {
    @Serializable
    data object Start : Destination
    @Serializable
    data object Flavor : Destination
    @Serializable
    data object Pickup : Destination
    @Serializable
    data object Summary : Destination
}