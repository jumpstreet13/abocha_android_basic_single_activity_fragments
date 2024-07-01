package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.components.BaseButton
import com.example.cupcake.components.BaseTopBar
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(viewModel: OrderViewModel, onNavigateToFlavor: () -> Unit) {
    Scaffold(
        topBar = {
            BaseTopBar(title = stringResource(R.string.app_name))
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            onSetQuantity = { quantity ->
                viewModel.setQuantity(quantity)
                onNavigateToFlavor()
            }
        )
    }
}

@Composable
private fun Content(modifier: Modifier, onSetQuantity: (Int) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(300.dp),
            painter = painterResource(R.drawable.cupcake),
            contentScale = ContentScale.None,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.order_cupcakes),
            color = colorResource(R.color.material_on_background_emphasis_medium),
            style = TextStyle(fontSize = 34.sp)
        )
        val buttonModifier = Modifier.defaultMinSize(minWidth = 250.dp)
        Spacer(modifier = Modifier.height(24.dp))
        BaseButton(
            text = stringResource(R.string.one_cupcake),
            onClick = { onSetQuantity(1) },
            modifier = buttonModifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        BaseButton(
            text = stringResource(R.string.six_cupcakes),
            onClick = { onSetQuantity(6) },
            modifier = buttonModifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        BaseButton(
            text = stringResource(R.string.twelve_cupcakes),
            onClick = { onSetQuantity(12) },
            modifier = buttonModifier
        )
    }
}
