package com.example.cupcake.navigation

import com.arkivanov.decompose.ComponentContext

class StartComponent(
    componentContext: ComponentContext,
    val onOrderCupcakeClick: (Int) -> Unit,
): ComponentContext by componentContext
