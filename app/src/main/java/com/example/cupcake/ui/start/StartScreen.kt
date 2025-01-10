package com.example.cupcake.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onQuantityCupCakeClick: (count: Int) -> Unit,
    viewModel: OrderViewModel = viewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
    ) { paddingValues ->
        val context = LocalContext.current
        StartScreenContent(
            modifier = Modifier.padding(paddingValues),
            onQuantitySelect = { quantity ->
                viewModel.setQuantity(quantity)
                if (viewModel.hasNoFlavorSet()) viewModel.setFlavor(context.getString(R.string.vanilla))
                onQuantityCupCakeClick(quantity)
            },
        )
    }
}

@Composable
private fun StartScreenContent(
    modifier: Modifier = Modifier,
    onQuantitySelect: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Image(
            modifier = Modifier
                .size(300.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(24.dp),
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "cupcake",
            contentScale = ContentScale.Inside,
        )

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = stringResource(R.string.order_cupcakes),
            fontSize = 28.sp
        )
        Button(
            modifier = Modifier
                .padding(top = 24.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally),
            onClick = { onQuantitySelect(1) },
        ) {
            Text(
                text = stringResource(R.string.one_cupcake),
                fontSize = 14.sp
            )
        }

        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally),
            onClick = { onQuantitySelect(6) },
        ) {
            Text(
                text = stringResource(R.string.six_cupcakes),
                fontSize = 14.sp
            )
        }

        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .width(250.dp)
                .align(alignment = Alignment.CenterHorizontally),
            onClick = { onQuantitySelect(12) },
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