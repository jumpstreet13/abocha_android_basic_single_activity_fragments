package com.example.cupcake.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.cupcake.ui.theme.WhiteTintRippleTheme
import com.example.cupcake.ui.theme.onTopAppBarContainer
import com.example.cupcake.ui.theme.topAppBarContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeTopBar(
    title: String,
    showUpArrow: Boolean = false,
    onNavigateUp: () -> Unit = {}
) {
    CompositionLocalProvider(LocalRippleTheme provides WhiteTintRippleTheme) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.topAppBarContainer,
                titleContentColor = MaterialTheme.colorScheme.onTopAppBarContainer
            ),
            title = {
                Text(text = title)
            },
            navigationIcon = {
                if (showUpArrow) {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }
}