package com.example.cupcake.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cupcake.util.IMAGE_SIZE
import com.example.cupcake.util.MARGIN_2
import com.example.cupcake.R
import com.example.cupcake.util.MARGIN
import com.example.cupcake.design.Button

@Composable
fun StartScreen(onOrderCupcake: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(MARGIN_2))
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,  // Accessibility description
            contentScale = ContentScale.Inside,
            modifier = Modifier.size(IMAGE_SIZE)
        )
        Text(
            text = stringResource(id = R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = colorResource(id = com.google.android.material.R.color.material_on_background_emphasis_medium)
        )
        Spacer(modifier = Modifier.height(MARGIN))
        Button(
            text = stringResource(id = R.string.one_cupcake),
            onClick = { onOrderCupcake(1) },
        )
        Spacer(modifier = Modifier.height(MARGIN))
        Button(
            text = stringResource(id = R.string.six_cupcakes),
            onClick = { onOrderCupcake(6) },
        )
        Spacer(modifier = Modifier.height(MARGIN))
        Button(
            text = stringResource(id = R.string.twelve_cupcakes),
            onClick = { onOrderCupcake(12) },
        )
    }
}
