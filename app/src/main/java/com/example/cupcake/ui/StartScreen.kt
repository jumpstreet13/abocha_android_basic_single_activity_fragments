package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.example.cupcake.ui.view.Toolbar

@Composable
fun StartScreen(
    onCupcakesSelect: (numberCupcakes: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(text = stringResource(id = R.string.app_name))
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.cupcake),
                contentDescription = stringResource(
                    id = R.string.app_name
                ),
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Inside,
            )
            Text(
                text = stringResource(id = R.string.order_cupcakes),
                style = MaterialTheme.typography.h4,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 250.dp),
                onClick = { onCupcakesSelect.invoke(1) }
            ) {
                Text(text = stringResource(id = R.string.one_cupcake))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 250.dp),
                onClick = { onCupcakesSelect.invoke(6) }) {
                Text(text = stringResource(id = R.string.six_cupcakes))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 250.dp),
                onClick = { onCupcakesSelect.invoke(12) }) {
                Text(text = stringResource(id = R.string.twelve_cupcakes))
            }
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    CupcakeTheme {
        StartScreen(
            onCupcakesSelect = {}
        )
    }
}