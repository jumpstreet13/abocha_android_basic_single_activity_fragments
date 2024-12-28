package com.example.cupcake.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cupcake.util.MARGIN
import com.example.cupcake.util.MARGIN_2
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.design.Button
import com.example.cupcake.design.OutlinedButton

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    onCancel: () -> Unit,
    onSend: () -> Unit
) {
    val quantity = viewModel.quantity.observeAsState()
    val flavor = viewModel.flavor.observeAsState()
    val date = viewModel.date.observeAsState()
    val price = viewModel.price.observeAsState()

    SummaryPage(
        quantity = quantity,
        flavor = flavor,
        pickupDate = date,
        totalPrice = price,
        onCancelOrder = onCancel,
        onSendOrder = onSend
    )
}

@Composable
private fun SummaryPage(
    quantity: State<Int?>,
    flavor: State<String?>,
    pickupDate: State<String?>,
    totalPrice: State<String?>,
    onCancelOrder: () -> Unit,
    onSendOrder: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MARGIN)
    ) {
        InfoItem(
            orderItemTitle = stringResource(id = R.string.quantity),
            orderItemText = pluralStringResource(
                id = R.plurals.cupcakes,
                count = quantity.value ?: 0,
                quantity.value ?: 0
            ),
        )

        InfoItem(
            orderItemTitle = stringResource(id = R.string.flavor),
            orderItemText = flavor.value.orEmpty(),
        )

        InfoItem(
            orderItemTitle = stringResource(id = R.string.pickup_date),
            orderItemText = pickupDate.value.orEmpty(),
        )

        Spacer(modifier = Modifier.height(MARGIN_2))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(
                id = R.string.total_price,
                totalPrice.value.orEmpty()
            ).uppercase(),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(MARGIN))

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.send),
            onClick = onSendOrder
        )

        Spacer(modifier = Modifier.height(MARGIN_2))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.cancel),
            onClick = onCancelOrder
        )
    }
}

@Composable
private fun InfoItem(
    orderItemTitle: String,
    orderItemText: String,
) {
    Column {

        Spacer(modifier = Modifier.height(MARGIN))

        Text(
            text = orderItemTitle.uppercase(),
            style = MaterialTheme.typography.body1
        )

        Text(
            text = orderItemText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MARGIN)
        )
    }
}