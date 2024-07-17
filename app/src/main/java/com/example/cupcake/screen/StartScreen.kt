package com.example.cupcake.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.theme.CupcakeTheme

@Preview
@Composable
private fun StartScreenPreview() {
    CupcakeTheme {
        StartScreen {}
    }
}

@Composable
fun StartScreen(
    orderCupcake: (Int) -> Unit
) {
    Scaffold { paddingValues ->
        StartScreenContent(
            paddingValues = paddingValues,
            orderCupcake = orderCupcake
        )
    }
}

@Composable
private fun StartScreenContent(
    paddingValues: PaddingValues,
    orderCupcake: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),
            contentScale = ContentScale.None,
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "Логотип"
        )

        Text(
            text = stringResource(id = R.string.order_cupcakes),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.widthIn(min = 250.dp),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(1) }
        ) {
            Text(
                text = stringResource(id = R.string.one_cupcake).uppercase(),
                color = colorResource(id = R.color.white)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.widthIn(min = 250.dp),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(6) }
        ) {
            Text(
                text = stringResource(id = R.string.six_cupcakes).uppercase(),
                color = colorResource(id = R.color.white)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.widthIn(min = 250.dp),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(12) }
        ) {
            Text(
                text = stringResource(id = R.string.twelve_cupcakes).uppercase(),
                color = colorResource(id = R.color.white)
            )
        }
    }
}