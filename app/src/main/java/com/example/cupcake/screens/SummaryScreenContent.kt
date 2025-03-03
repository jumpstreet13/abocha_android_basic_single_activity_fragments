package com.example.cupcake.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.CupcakeAppTheme

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    sendOrder: () -> Unit,
    cancel: () -> Unit,
) {
    val quantity = viewModel.quantity.collectAsState()
    val flavor = viewModel.flavor.collectAsState()
    val pickupDate = viewModel.date.collectAsState()
    val price = viewModel.price.collectAsState()

    SummaryScreenContent(
        modifier = modifier,
        quantity = pluralStringResource(R.plurals.cupcakes, quantity.value, quantity.value),
        flavor = flavor.value,
        pickupDate = pickupDate.value,
        price = stringResource(R.string.total_price, price.value).uppercase(),
        sendOrder = sendOrder,
        cancel = {
            viewModel.resetOrder()
            cancel()
        },
    )
}

@Composable
fun SummaryScreenContent(
    modifier: Modifier = Modifier,
    quantity: String,
    flavor: String,
    pickupDate: String,
    price: String,
    sendOrder: () -> Unit,
    cancel: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(horizontal = dimensionResource(R.dimen.side_margin)),
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.side_margin)))

        Text(
            text = stringResource(R.string.quantity).uppercase(),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
        Text(
            text = quantity,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.flavor).uppercase(),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
        Text(
            text = flavor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.pickup_date).uppercase(),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
        Text(
            text = pickupDate,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = price,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = sendOrder,
        ) {
            Text(text = stringResource(R.string.send).uppercase())
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
            onClick = cancel,
        ) {
            Text(text = stringResource(R.string.cancel).uppercase())
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun SummaryScreenPreview() {
    CupcakeAppTheme {
        SummaryScreenContent(
            quantity = pluralStringResource(R.plurals.cupcakes, 6, 6),
            flavor = "Vanilla",
            pickupDate = "Sunday",
            price = stringResource(R.string.total_price, "$10").uppercase(),
            sendOrder = {},
            cancel = {},
        )
    }
}