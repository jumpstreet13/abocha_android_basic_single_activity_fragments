package com.example.cupcake.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}