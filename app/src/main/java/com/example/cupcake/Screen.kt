package com.example.cupcake

sealed class Screen(val route: String) {
    data object Start : Screen(START)
    data object Flavor : Screen(FLAVOR)
    data object Pickup : Screen(PICKUP)
    data object Summary : Screen(SUMMARY)

    private companion object {
        const val START = "start"
        const val FLAVOR = "flavor"
        const val PICKUP = "pickup"
        const val SUMMARY = "summary"

    }

}