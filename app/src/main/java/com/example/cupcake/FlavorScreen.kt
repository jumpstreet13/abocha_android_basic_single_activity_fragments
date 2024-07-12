package com.example.cupcake

import Toolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FlavorScreen(
    onRadioButtonClicked: (String) -> Unit,
    selectedFlavor: String,
    price: String,
    onNextClick: () -> Unit,
    onCancelClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(topBar = { Toolbar(title = stringResource(id = R.string.choose_flavor)) { onBackClick() } }) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val flavors = listOf(
                stringResource(id = R.string.vanilla),
                stringResource(id = R.string.chocolate),
                stringResource(id = R.string.red_velvet),
                stringResource(id = R.string.salted_caramel),
                stringResource(id = R.string.coffee),
            )

            flavors.forEach { flavor ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedFlavor == flavor,
                        onClick = { onRadioButtonClicked(flavor) },
                        colors = RadioButtonDefaults.colors()
                    )
                    Text(flavor)
                }
            }

            Divider(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.subtotal_price, price),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.subtitle1,
            )

            Row {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(0.5f),
                ) {
                    Text("Cancel")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { onNextClick() },
                    modifier = Modifier.weight(0.5f),
                ) {
                    Text("Next")
                }
            }
        }
    }
}