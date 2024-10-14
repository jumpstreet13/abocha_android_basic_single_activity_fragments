package com.example.cupcake.ui.screens

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.cupcake.model.ScreenTransitionHandler
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@Composable
internal fun isUiEnabled(screenTransitionHandler: ScreenTransitionHandler) =
    !screenTransitionHandler.isScreenTransitionInProgress.collectAsState().value


@Composable
internal fun BackPressedHandlerWithDisablingClicks(screenTransitionHandler: ScreenTransitionHandler) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = !backPressHandled) {
        backPressHandled = true
        screenTransitionHandler.setScreenTransitionInProgress(true)

        // BackHandler uses LocalOnBackPressedDispatcherOwner under the hood, so we need to wait
        // for the next recomposition to make sure the back handler is disabled, then we can perform onBackPressed:
        coroutineScope.launch {
            awaitFrame()
            onBackPressedDispatcher?.onBackPressed()
            backPressHandled = false
        }
    }
}