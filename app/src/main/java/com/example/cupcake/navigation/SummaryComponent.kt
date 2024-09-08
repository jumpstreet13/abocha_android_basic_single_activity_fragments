package com.example.cupcake.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class SummaryComponent(
    val quantity: Int,
    val price: Value<String>,
    val date: Value<String>,
    val flavor: Value<String>,
    val onBackClick: () -> Unit,
    val onCancelClick: () -> Unit,
    componentContext: ComponentContext,
): ComponentContext by componentContext