package com.example.cupcake.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class PickupComponent(
    componentContext: ComponentContext,
    val dates: List<String>,
    val price: Value<String>,
    val selectedDate: Value<String>,
    val onDateSelected: (String) -> Unit,
    val onCancelClick: () -> Unit,
    val onNextClick: () -> Unit,
    val onBackClick: () -> Unit,
): ComponentContext by componentContext