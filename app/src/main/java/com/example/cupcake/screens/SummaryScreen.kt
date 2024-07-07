package com.example.cupcake.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.components.BaseButton
import com.example.cupcake.components.BaseTopBar
import com.example.cupcake.components.ButtonType
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    onNavigateToBack: () -> Unit,
    onCancel: () -> Unit
) {
    val priceValue by viewModel.price.observeAsState()
    val quantityValue by viewModel.quantity.observeAsState()
    val flavorValue by viewModel.flavor.observeAsState()
    val dateValue by viewModel.date.observeAsState()
    Scaffold(
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.order_summary),
                onBackClick = onNavigateToBack
            )
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            quantity = quantityValue.takeIf { it != null },
            flavor = flavorValue,
            date = dateValue,
            price = priceValue.toString(),
            onCancelClick = {
                viewModel.resetOrder()
                onCancel()
            },
            onSendOrderClick = viewModel::sendOrder
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    quantity: Int?,
    flavor: String?,
    date: String?,
    price: String,
    onCancelClick: () -> Unit,
    onSendOrderClick: (String) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        if (quantity != null) {
            Cell(
                title = stringResource(R.string.quantity),
                value = quantity.toString()
            )
        }
        if (flavor != null) {
            Cell(
                title = stringResource(R.string.flavor),
                value = flavor
            )
        }
        if (date != null) {
            Cell(
                title = stringResource(R.string.pickup_date),
                value = date
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = stringResource(
                    R.string.total_price,
                    price
                ).uppercase(),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        BaseButton(
            text = stringResource(R.string.send),
            onClick = {
                onSendOrderClick(
                    getOrderString(
                        context = context,
                        quantity = quantity ?: 0,
                        flavor = flavor ?: "",
                        date = date ?: "",
                        price = price
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        BaseButton(
            text = stringResource(R.string.cancel),
            onClick = onCancelClick,
            type = ButtonType.SECONDARY,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Cell(title: String, value: String) {
    Text(
        text = title.uppercase(),
        style = TextStyle(fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = value,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
    Spacer(modifier = Modifier.height(16.dp))
}

private fun getOrderString(context: Context, quantity: Int, flavor: String, date: String, price: String) =
    context.getString(
        R.string.order_details,
        context.resources.getQuantityString(R.plurals.cupcakes, quantity, quantity),
        flavor,
        date,
        price
    )
