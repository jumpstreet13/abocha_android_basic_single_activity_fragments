package com.example.cupcake.compose.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.compose.core.CupcakeButton
import com.example.cupcake.compose.core.OutlinedCupcakeButton

@Composable
fun SummaryScreen(
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
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.quantity),
            orderItemText = pluralStringResource(
                id = R.plurals.cupcakes,
                count = quantity.value ?: 0
            ),
        )

        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.flavor),
            orderItemText = flavor.value.orEmpty(),
        )

        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.pickup_date),
            orderItemText = pickupDate.value.orEmpty(),
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_between_elements)))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(
                id = R.string.total_price,
                totalPrice.value.orEmpty()
            ).uppercase(),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))

        CupcakeButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.send),
            onButtonClick = onSendOrder
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_between_elements)))

        OutlinedCupcakeButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.cancel),
            onButtonClick = onCancelOrder
        )
    }
}

@Composable
fun OrderOptionItem(
    orderItemTitle: String,
    orderItemText: String,
) {
    Column {

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.order_summary_margin)))

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
                .padding(vertical = 16.dp)
        )
    }
}
