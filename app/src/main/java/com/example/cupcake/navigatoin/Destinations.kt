package com.example.cupcake.navigatoin

import kotlinx.serialization.Serializable

@Suppress("ConvertObjectToDataObject")
sealed interface Destination {
    @Serializable
    object Start : Destination
    @Serializable
    object Flavor : Destination
    @Serializable
    object Pickup : Destination
    @Serializable
    object Summary : Destination
}