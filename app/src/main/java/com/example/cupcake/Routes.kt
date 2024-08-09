package com.example.cupcake

sealed class Routes(val route: String){
    data object StartScreen: Routes("start")
    data object PickupScreen: Routes("pickup")
    data object FlavorScreen: Routes("flavor")
    data object SummaryScreen: Routes("summary")

}
