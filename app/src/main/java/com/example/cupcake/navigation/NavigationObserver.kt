package com.example.cupcake.navigation

import com.example.cupcake.di.ApplicationScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ApplicationScope
class NavigationObserver @Inject constructor() {
    private val _navigateState = MutableSharedFlow<NavigationIntent>()
    val navigateState = _navigateState.asSharedFlow()

    suspend fun navigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    ) {
        val intent = NavigationIntent.NavigateTo(
            route,
            popUpToRoute,
            inclusive,
            isSingleTop
        )
        _navigateState.emit(intent)
    }

    suspend fun navigateUp(
        route: String? = null,
        inclusive: Boolean = false,
    ) {
        val intent = NavigationIntent.NavigateBack(route, inclusive)
        _navigateState.emit(intent)
    }
}