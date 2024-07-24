package com.example.cupcake.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.theme.CupcakeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Preview
@Composable
private fun SummaryScreenPreview() {
    CupcakeTheme {
        PickupScreen(
            price = MutableStateFlow(0.0).asStateFlow().collectAsState(),
            dateOptions = listOf("Пятница", "Суббота"),
            date = MutableStateFlow("").asStateFlow().collectAsState(),
            setDate = {},
            cancelOrder = {},
            goToNextScreen = {}
        )
    }
}

@Composable
fun PickupScreen(
    price: State<Double>,
    dateOptions: List<String>,
    date: State<String>,
    setDate: (String) -> Unit,
    cancelOrder: () -> Unit,
    goToNextScreen: () -> Unit
) {
    Scaffold { paddingValues ->
        PickupScreenContent(
            paddingValues = paddingValues,
            price = price,
            dateOptions = dateOptions,
            date = date,
            setDate = setDate,
            cancelOrder = cancelOrder,
            goToNextScreen = goToNextScreen
        )
    }
}

@Composable
private fun PickupScreenContent(
    paddingValues: PaddingValues,
    price: State<Double>,
    dateOptions: List<String>,
    date: State<String>,
    setDate: (String) -> Unit,
    cancelOrder: () -> Unit,
    goToNextScreen: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(date.value) }

        Column(Modifier.selectableGroup()) {
            dateOptions.forEach { text ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            setDate(text)
                            onOptionSelected(text)
                        }
                    )

                    Text(text = text)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Subtotal %s ${price.value}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { cancelOrder() }
            ) {
                Text(
                    text = stringResource(id = R.string.cancel).uppercase(),
                )
            }

            Button(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { goToNextScreen() }
            ) {
                Text(
                    text = stringResource(id = R.string.next).uppercase(),
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}