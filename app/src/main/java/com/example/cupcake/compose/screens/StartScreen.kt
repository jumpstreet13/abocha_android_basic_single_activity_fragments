package com.example.cupcake.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.compose.core.CupcakeButton


@Composable
fun StartScreen(
    onButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        Image(
            modifier = Modifier.size(dimensionResource(id = R.dimen.image_size)),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = colorResource(id = com.google.android.material.R.color.material_on_background_emphasis_medium),
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.side_margin)))

        OrderCupcakeButton(
            text = stringResource(id = R.string.one_cupcake),
            onButtonClick = { onButtonClick(1) }
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        OrderCupcakeButton(
            text = stringResource(id = R.string.six_cupcakes),
            onButtonClick = { onButtonClick(6) }
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        OrderCupcakeButton(
            text = stringResource(id = R.string.twelve_cupcakes),
            onButtonClick = { onButtonClick(12) }
        )
    }
}

@Composable
fun OrderCupcakeButton(
    text: String,
    onButtonClick: () -> Unit,
) {
    CupcakeButton(
        modifier = Modifier.widthIn(min = dimensionResource(id = R.dimen.order_cupcake_button_width)),
        text = text,
        onButtonClick = onButtonClick
    )
}
