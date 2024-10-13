package com.example.cupcake.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.cupcake.model.ScreenTransitionHandler

@Composable
internal fun isUiEnabled(screenTransitionHandler: ScreenTransitionHandler) =
    !screenTransitionHandler.isScreenTransitionInProgress.collectAsState().value