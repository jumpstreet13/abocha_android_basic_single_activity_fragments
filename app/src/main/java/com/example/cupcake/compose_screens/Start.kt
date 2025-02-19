package com.example.cupcake.compose_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R

@Stable
@Composable
fun Start(
    navigate: () -> Unit,
    setAmount: (value:Int) -> Unit,
    setEmptyOrder: () -> Unit
) {
    setEmptyOrder()
    Column (Modifier
        .padding(dimensionResource(R.dimen.side_margin))
        .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.cupcake),
            contentDescription = "",
            contentScale = ContentScale.None,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(top = dimensionResource(R.dimen.margin_between_elements))
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.order_cupcakes),
            color = colorResource(com.google.android.material.R.color.material_on_background_emphasis_medium),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.side_margin))
                .align(Alignment.CenterHorizontally)
        )

        OrderButton(
            navigate = navigate,
            setAmount = setAmount,
            buttonText = stringResource(R.string.one_cupcake),
            cakesAmount = 1
        )

        OrderButton(
            navigate = navigate,
            setAmount = setAmount,
            buttonText = stringResource(R.string.six_cupcakes),
            cakesAmount = 6
        )

        OrderButton(
            navigate = navigate,
            setAmount = setAmount,
            buttonText = stringResource(R.string.twelve_cupcakes),
            cakesAmount = 12
        )
    }
}

@Composable
fun ColumnScope.OrderButton(
    navigate: () -> Unit,
    setAmount: (value:Int) -> Unit,
    buttonText: String,
    cakesAmount: Int
) {
    Button(
        onClick = {
            setAmount(cakesAmount)
            navigate()
        },
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.margin_between_elements))
            .widthIn(min = dimensionResource(R.dimen.order_cupcake_button_width))
            .align(Alignment.CenterHorizontally),
    ) {
        Text(text = buttonText)
    }
}