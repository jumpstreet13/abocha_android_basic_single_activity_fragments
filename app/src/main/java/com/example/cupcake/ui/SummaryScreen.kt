package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.ui.view.Toolbar

@Composable
fun SummaryScreen(
    quantity: Int,
    flavor: String,
    date: String,
    price: String,
    onBackButtonClick: () -> Unit,
    onCancelClick: () -> Unit,
    onSendClick: (text: String) -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(
                text = stringResource(id = R.string.order_summary),
                onBackButtonClick = onBackButtonClick
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val orderSummary = stringResource(
                R.string.order_details,
                pluralStringResource(R.plurals.cupcakes, quantity, quantity),
                flavor,
                date,
                price,
            )
            val list = listOf(
                stringResource(id = R.string.quantity).uppercase() to quantity.toString(),
                stringResource(id = R.string.flavor).uppercase() to flavor,
                stringResource(id = R.string.pickup_date).uppercase() to date,
            )
            list.forEach { element ->
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = element.first,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = element.second,
                    fontWeight = FontWeight.Bold,
                )
                Divider(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.total_price, price),
                textAlign = TextAlign.End,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSendClick(orderSummary) }
            ) {
                Text(text = stringResource(id = R.string.send))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCancelClick
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    CupcakeTheme {
        SummaryScreen(
            quantity = 1,
            flavor = "Blood",
            date = "Friday 13",
            price = "0$",
            onBackButtonClick = {},
            onCancelClick = {},
            onSendClick = {},
        )
    }
}