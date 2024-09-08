package com.example.cupcake.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.navigation.StartComponent
import com.google.android.material.R as MaterialR

@Composable
fun StartScreenUi(component: StartComponent) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = { Text("Cupcake") },
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(top = dimensionResource(R.dimen.margin_between_elements))
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.cupcake),
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = stringResource(R.string.order_cupcakes),
                modifier = Modifier.padding(bottom = 16.dp),
                color = colorResource(id = MaterialR.color.material_on_background_emphasis_medium),
                style = MaterialTheme.typography.h4,
            )
            Button(
                onClick = { component.onOrderCupcakeClick(1) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .defaultMinSize(minWidth = 250.dp)
            ) {
                Text(stringResource(R.string.one_cupcake).uppercase())
            }
            Button(
                onClick = { component.onOrderCupcakeClick(6) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .defaultMinSize(minWidth = 250.dp)
            ) {
                Text(stringResource(R.string.six_cupcakes).uppercase())
            }
            Button(
                onClick = { component.onOrderCupcakeClick(12) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .defaultMinSize(minWidth = 250.dp)
            ) {
                Text(stringResource(R.string.twelve_cupcakes).uppercase())
            }
        }
    }
}
