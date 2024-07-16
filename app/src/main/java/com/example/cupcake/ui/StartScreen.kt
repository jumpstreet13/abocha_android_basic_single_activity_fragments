package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R

@Composable
fun StartScreen(
    onAmountSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val margin_between_elements = dimensionResource(R.dimen.margin_between_elements)

    Box(
        Modifier.fillMaxSize()
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_between_elements)),
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.cupcake), contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(top = margin_between_elements)
                    .width(dimensionResource(R.dimen.image_size))
                    .height(dimensionResource(R.dimen.image_size))
            )

            Text(
                text = stringResource(R.string.order_cupcakes),
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(bottom = margin_between_elements)
                    .alpha(0.7F)
            )

            PrimaryButton(
                stringResource(R.string.one_cupcake),
                onClick = { onAmountSelected(1) },
                modifier = Modifier.padding(top = margin_between_elements)
            )
            PrimaryButton(
                stringResource(R.string.six_cupcakes),
                onClick = { onAmountSelected(6) },
                modifier = Modifier.padding(top = margin_between_elements)
            )
            PrimaryButton(
                stringResource(R.string.twelve_cupcakes),
                onClick = { onAmountSelected(12) },
                modifier = Modifier.padding(top = margin_between_elements)
            )
        }

        MyColumn(Modifier
            .background(Color.Blue.copy(alpha = 0.1f))
            .align(Alignment.TopCenter)
        ) {
            Text("Otus")
            Text("Android")
            Text("Professional")
            Text("Compose")
        }
    }
}

@Preview(device = Devices.NEXUS_6)
@Composable
fun StartScreenPreview() {
    CupcakeTheme {
        StartScreen(onAmountSelected = {})
    }
}