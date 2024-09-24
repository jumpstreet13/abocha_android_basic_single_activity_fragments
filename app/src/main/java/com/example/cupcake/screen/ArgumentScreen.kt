package com.example.cupcake.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.theme.CupcakeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Preview
@Composable
private fun ArgumentScreenPreview() {
    CupcakeTheme {
        ArgumentScreen(
            price = MutableStateFlow(0.0).asStateFlow().collectAsState(),
            quantity = MutableStateFlow(0).asStateFlow().collectAsState(),
            flavor = MutableStateFlow("").asStateFlow().collectAsState(),
            date = MutableStateFlow("").asStateFlow().collectAsState(),
            sendOrder = {},
            cancelOrder = {}
        )
    }
}

@Composable
fun ArgumentScreen(
    price: State<Double>,
    quantity: State<Int>,
    flavor: State<String>,
    date: State<String>,
    sendOrder: () -> Unit,
    cancelOrder: () -> Unit
) {
    Scaffold { paddingValues ->
        ArgumentScreenContent(
            paddingValues = paddingValues,
            price = price,
            quantity = quantity,
            flavor = flavor,
            date = date,
            cancelOrder = cancelOrder,
            sendOrder = sendOrder
        )
    }
}

@Composable
private fun ArgumentScreenContent(
    paddingValues: PaddingValues,
    price: State<Double>,
    quantity: State<Int>,
    flavor: State<String>,
    date: State<String>,
    cancelOrder: () -> Unit,
    sendOrder: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ItemData(
            stringResource(id = R.string.quantity),
            quantity.value.toString()
        )

        ItemData(
            stringResource(id = R.string.flavor),
            flavor.value
        )

        ItemData(
            stringResource(id = R.string.pickup_date),
            date.value
        )

        Text(
            text = "Total $${price.value}".uppercase(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { sendOrder() }
            ) {
                Text(
                    text = stringResource(id = R.string.send).uppercase(),
                    color = colorResource(id = R.color.white)
                )
            }

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { cancelOrder() }
            ) {
                Text(
                    text = stringResource(id = R.string.cancel).uppercase(),
                )
            }
        }
    }
}

@Composable
private fun ItemData(
    labelText: String,
    text: String
) {
    Text(
        text = labelText.uppercase(),
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 20.sp
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )

    Spacer(modifier = Modifier.height(16.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(16.dp))
}