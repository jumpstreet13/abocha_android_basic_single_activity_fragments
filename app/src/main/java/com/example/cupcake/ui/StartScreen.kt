package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    )
    { paddingValues ->
        StartScreenContent(modifier.padding(paddingValues))
    }
}

@Composable
private fun StartScreenContent(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CupcakeImage()
        OrderCupcakesText()
        OrderCupcakeButton()
        OrderCupcakeButton()
        OrderCupcakeButton()
    }
}

@Composable
fun CupcakeImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.cupcake),
        contentDescription = "Cupcake",
        contentScale = ContentScale.Inside,
        modifier = modifier
            .width(300.dp)
            .height(300.dp)
            .padding(top = 8.dp)
    )
}

@Composable
fun OrderCupcakesText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.order_cupcakes),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        modifier = modifier.padding(bottom = 16.dp),
    )
}

@Composable
fun OrderCupcakeButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* TODO */ },
        modifier = modifier
            .padding(top = 8.dp)
            .widthIn(min = 250.dp)
    ) {
        Text(text = stringResource(id = R.string.one_cupcake))
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenLightPreview() {
    CupcakeTheme(darkTheme = false) {
        StartScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenDarkPreview() {
    CupcakeTheme(darkTheme = true) {
        StartScreen()
    }
}