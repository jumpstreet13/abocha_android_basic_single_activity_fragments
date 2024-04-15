package com.example.cupcake

sealed class NavScreen(var route: String) {
    object StartScreenNav : NavScreen("StartScreenNav")
    object FlavorScreenNav : NavScreen("FlavorScreenNav")
    object PickupScreenNav : NavScreen("PickupScreenNav")
    object SummaryScreenNav : NavScreen("SummaryScreenNav")
}
