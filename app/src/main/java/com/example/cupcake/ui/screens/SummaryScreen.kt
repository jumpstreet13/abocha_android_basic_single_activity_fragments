package com.example.cupcake.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.widgets.CupcakeTopBar

@Composable
fun SummaryScreen(
    sharedViewModel: OrderViewModel,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CupcakeTopBar(
                title = stringResource(id = R.string.choose_flavor),
                showUpArrow = true,
                onNavigateUp = onNavigateBack
            )
        }
    )
    { paddingValues ->
        Text("Summary Screen", modifier = Modifier.padding(paddingValues))
    }
}