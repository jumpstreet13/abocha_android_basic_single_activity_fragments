package com.example.cupcake.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class FlavorComponent(
    componentContext: ComponentContext,
    val price: Value<String>,
    val selectedFlavor: Value<String>,
    val onFlavorSelected: (String) -> Unit,
    val onCancelClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onBackClick: () -> Unit,
): ComponentContext by componentContext
