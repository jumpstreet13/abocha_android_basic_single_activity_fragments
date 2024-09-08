package com.example.cupcake.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.cupcake.R
import com.example.cupcake.navigation.PickupComponent
import com.example.cupcake.ui.MyApplicationTheme
import com.example.cupcake.ui.elements.Radio

@Composable
fun PickupScreenUi(pickupComponent: PickupComponent) {
    val price by pickupComponent.price.subscribeAsState()
    val selectedDate by pickupComponent.selectedDate.subscribeAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.choose_flavor)) },
                navigationIcon = {
                    IconButton(onClick = pickupComponent.onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        val scrollableState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollableState)
                .selectableGroup()
                .padding(top = 16.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            for (date in pickupComponent.dates) {
                Radio(
                    text = date,
                    selected = date == selectedDate,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { pickupComponent.onDateSelected(date) },
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Subtotal $price",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.side_margin)),
                textAlign = TextAlign.End
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.side_margin)),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedButton(
                    onClick = pickupComponent.onCancelClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(stringResource(R.string.cancel).uppercase())
                }
                Button(
                    onClick = pickupComponent.onNextClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(stringResource(R.string.next).uppercase())
                }
            }
        }
    }
}