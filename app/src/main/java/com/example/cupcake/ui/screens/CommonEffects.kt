package com.example.cupcake.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.example.cupcake.model.ScreenTransitionHandler

@Composable
fun ScreenTransitionInProgressFinishedEffect(screenTransitionHandler: ScreenTransitionHandler) {
    DisposableEffect(Unit) {
        onDispose {
            screenTransitionHandler.setScreenTransitionInProgress(false)
        }
    }
}