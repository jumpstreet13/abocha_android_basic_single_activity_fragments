package com.example.cupcake.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.ui_components.CupcakeButton
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: OrderViewModel = viewModel(factory = getVmFactory())
) {
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Image(
                    modifier = Modifier.size(300.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.cupcake),
                    contentDescription = null
                )
            }
            
            Box(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.order_cupcakes),
                    color = colorResource(id = R.color.material_on_background_emphasis_medium),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            CupcakeButton(
                modifier = Modifier.widthIn(min = 250.dp),
                text = stringResource(id = R.string.one_cupcake),
                onClick = {
                    viewModel.orderCupcake(1)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CupcakeButton(
                modifier = Modifier.widthIn(min = 250.dp),
                text = stringResource(id = R.string.six_cupcakes),
                onClick = {
                    viewModel.orderCupcake(6)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CupcakeButton(
                modifier = Modifier.widthIn(min = 250.dp),
                text = stringResource(id = R.string.twelve_cupcakes),
                onClick = {
                    viewModel.orderCupcake(12)
                }
            )
        }
    }
}

