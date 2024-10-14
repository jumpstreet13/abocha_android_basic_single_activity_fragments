package com.example.cupcake.model

import kotlinx.coroutines.flow.StateFlow

interface ScreenTransitionHandler {
    val isScreenTransitionInProgress: StateFlow<Boolean>
    fun setScreenTransitionInProgress(isInProgress: Boolean)
}