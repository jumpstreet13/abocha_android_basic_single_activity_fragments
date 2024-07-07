package com.example.cupcake.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cupcake.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreenWrapper(
    title: String,
    onBackButtonClick: (() -> Unit)? = null,
    content: @Composable (BoxScope.() -> Unit)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = title, color = Color.White) },
                navigationIcon = {
                    onBackButtonClick?.let {
                        IconButton(onClick = it) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = if (isSystemInDarkTheme()) Color.Black else AppColors.pink400)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
    }
}