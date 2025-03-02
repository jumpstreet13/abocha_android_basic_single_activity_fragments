package com.example.cupcake.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

@Composable
fun PickupScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var thursdayIsSelected by remember { mutableStateOf(false) }
    var fridayIsSelected by remember { mutableStateOf(false) }
    var saturdayIsSelected by remember { mutableStateOf(false) }
    var sundayIsSelected by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = thursdayIsSelected,
                onClick = { thursdayIsSelected = true }
            )
            Text(text = "Thursday")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = fridayIsSelected,
                onClick = { fridayIsSelected = true }
            )
            Text(text = "Friday")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = saturdayIsSelected,
                onClick = { saturdayIsSelected = true }
            )
            Text(text = "Saturday")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = sundayIsSelected,
                onClick = { sundayIsSelected = true }
            )
            Text(text = "Sunday")
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Subtotal $10.00")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                shape = RoundedCornerShape(4.dp),
                onClick = { },
                modifier = Modifier.weight(0.8f),
            ) {
                Text(text = stringResource(R.string.cancel).uppercase())
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                shape = RoundedCornerShape(4.dp),
                onClick = { },
                modifier = Modifier.weight(0.8f),
            ) {
                Text(text = stringResource(R.string.next).uppercase())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun PickupScreenPreview() {
    MaterialTheme {
        PickupScreen()
    }
}