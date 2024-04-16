package com.example.cupcake.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.Gray1200

@Composable
fun MainScreen(viewModel: OrderViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Gray1200,
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
            Log.d("mainScreen", "MainScreen start")
            Navigation(navController = navController, viewModel = viewModel)
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {}
        }
    }
}
