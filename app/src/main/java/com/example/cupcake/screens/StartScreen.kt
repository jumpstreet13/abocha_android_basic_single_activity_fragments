package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onQuantityCupCakeClick: (count: Int) -> Unit,
    viewModel: OrderViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        val context = LocalContext.current
        StartScreenContent(
            onQuantitySelect = { quantity ->
                viewModel.setQuantity(quantity)
                if (viewModel.hasNoFlavorSet()) {
                    viewModel.setFlavor(context.getString(R.string.vanilla))
                }
                onQuantityCupCakeClick(quantity)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun StartScreenContent(
    onQuantitySelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(300.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(24.dp)
        )

        Text(
            text = stringResource(R.string.order_cupcakes),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 28.sp
        )
        Button(
            onClick = { onQuantitySelect(1) },
            shape = RectangleShape,
            modifier = Modifier
                .padding(top = 24.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.one_cupcake),
                fontSize = 14.sp
            )
        }

        Button(
            onClick = { onQuantitySelect(6) },
            shape = RectangleShape,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.six_cupcakes),
                fontSize = 14.sp
            )
        }

        Button(
            onClick = { onQuantitySelect(12) },
            shape = RectangleShape,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.twelve_cupcakes),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
@Preview
fun StartScreenPreview() {
    StartScreen(
        onQuantityCupCakeClick = {}
    )
}