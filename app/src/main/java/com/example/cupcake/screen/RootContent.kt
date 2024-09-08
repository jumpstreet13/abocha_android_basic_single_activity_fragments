package com.example.cupcake.screen

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.cupcake.navigation.RootComponent
import com.example.cupcake.navigation.RootComponent.Child
import com.example.cupcake.navigation.RootComponent.Child.Flavor
import com.example.cupcake.navigation.RootComponent.Child.Pickup
import com.example.cupcake.navigation.RootComponent.Child.Start
import com.example.cupcake.navigation.RootComponent.Child.Summary

@Composable
fun RootContent(component: RootComponent) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(slide()),
    ) {
        when (val child = it.instance) {
            is Start -> StartScreenUi(child.component)
            is Flavor -> FlavorScreenUi(child.component)
            is Pickup -> PickupScreenUi(child.component)
            is Summary -> SummaryScreenUi(child.component)
        }
    }
}