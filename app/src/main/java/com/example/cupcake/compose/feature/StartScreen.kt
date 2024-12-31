package com.example.cupcake.compose.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.compose.ui.designsystem.CupcakeButton
import com.example.cupcake.compose.ui.theme.CupcakeTheme
import com.example.cupcake.compose.ui.util.IMAGE_SIZE
import com.example.cupcake.compose.ui.util.MARGIN_BETWEEN_ELEMENTS
import com.example.cupcake.compose.ui.util.SIDE_MARGIN

@Composable
fun StartRoute(
    onButtonClick: (Int) -> Unit
) {
    StartScreen(onButtonClick)
}

@Composable
fun StartScreen(
    onButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SIDE_MARGIN),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(MARGIN_BETWEEN_ELEMENTS))

        Image(
            modifier = Modifier.size(IMAGE_SIZE),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = colorResource(id = com.google.android.material.R.color.material_on_background_emphasis_medium),
        )

        Spacer(modifier = Modifier.height(SIDE_MARGIN))

        CupcakeButton(
            text = stringResource(id = R.string.one_cupcake),
            onButtonClick = { onButtonClick(1) }
        )

        Spacer(modifier = Modifier.height(MARGIN_BETWEEN_ELEMENTS))

        CupcakeButton(
            text = stringResource(id = R.string.six_cupcakes),
            onButtonClick = { onButtonClick(6) }
        )

        Spacer(modifier = Modifier.height(MARGIN_BETWEEN_ELEMENTS))

        CupcakeButton(
            text = stringResource(id = R.string.twelve_cupcakes),
            onButtonClick = { onButtonClick(12) }
        )
    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    CupcakeTheme {
        StartScreen({})
    }
}