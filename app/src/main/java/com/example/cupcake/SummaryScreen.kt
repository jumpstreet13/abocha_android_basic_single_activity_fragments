package com.example.cupcake

import Toolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun SummaryScreen(
    onSendOrder: (String) -> Unit,
    onCancelOrder: () -> Unit,
    orderSummary: String,
    quantity: Int?,
    flavor: String,
    date: String,
    price: String,
    onBackClick: () -> Unit,
) {
    Scaffold(topBar = {
        Toolbar(
            title = stringResource(id = R.string.order_summary)
        ) { onBackClick() }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.quantity).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
            )
            Divider(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.flavor).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = flavor,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
            )
            Divider(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.pickup_date).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = date,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
            )
            Divider(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    id = R.string.total_price, price
                ).uppercase(Locale.getDefault()),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h6,
            )

            Button(
                onClick = { onSendOrder(orderSummary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.send))
            }

            OutlinedButton(
                onClick = onCancelOrder, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }
}
