package com.example.cupcake.summary_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui_components.CupcakeButton
import com.example.cupcake.ui_components.CupcakeOutlinedButton

@Composable
fun SummaryScreen(
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: OrderViewModel = viewModel(factory = getVmFactory())
) {
    Surface {
        val scrollState = rememberScrollState()
        val dateState = viewModel.date.collectAsState()
        val flavorState = viewModel.flavor.collectAsState()
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            SummaryItem(
                title = stringResource(id = R.string.quantity),
                value = viewModel.quantity.value.toString()
            )
            SummaryItem(
                title = stringResource(id = R.string.flavor),
                value = flavorState.value
            )
            SummaryItem(
                title = stringResource(id = R.string.pickup_date),
                value = dateState.value.toString()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(
                        id = R.string.total_price,
                        viewModel.price.value.toString()
                    ).uppercase(),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CupcakeButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.send),
                onClick = {
                    viewModel.send(context)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CupcakeOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.cancel),
                onClick = viewModel::cancelOrder
            )
        }
    }
}

@Composable
fun SummaryItem(
    title: String,
    value: String
) {
    Text(
        text = title.uppercase(),
    )
    Spacer(
        modifier = Modifier.height(4.dp)
    )
    Text(
        text = value,
        fontWeight = FontWeight.Bold
    )
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp)
    )
}