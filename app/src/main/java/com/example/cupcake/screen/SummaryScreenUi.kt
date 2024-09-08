package com.example.cupcake.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.cupcake.R
import com.example.cupcake.navigation.SummaryComponent

@Composable
fun SummaryScreenUi(summaryComponent: SummaryComponent) {
    val context = LocalContext.current
    val resources = context.resources
    val price by summaryComponent.price.subscribeAsState()
    val date by summaryComponent.date.subscribeAsState()
    val flavor by summaryComponent.flavor.subscribeAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.order_summary)) },
                navigationIcon = {
                    IconButton(onClick = summaryComponent.onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .selectableGroup()
                .padding(top = 16.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            OrderDetail(
                title = stringResource(R.string.quantity),
                value = "${summaryComponent.quantity} cupcakes",
                modifier = Modifier
                    .fillMaxWidth()
            )
            OrderDetail(
                title = stringResource(R.string.flavor),
                value = flavor,
                modifier = Modifier
                    .fillMaxWidth()
            )
            OrderDetail(
                title = stringResource(R.string.pickup_date),
                value = date,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.total_price, price).uppercase(),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.End),
                style = MaterialTheme.typography.h6
            )
            Button(
                onClick = {
                    // Construct the order summary text with information from the view model
                    val orderSummary = resources.getString(
                        R.string.order_details,
                        resources.getQuantityString(
                            R.plurals.cupcakes,
                            summaryComponent.quantity,
                            summaryComponent.quantity
                        ),
                        flavor,
                        date,
                        price
                    )

                    // Create an ACTION_SEND implicit intent with order details in the intent extras
                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.new_cupcake_order))
                        .putExtra(Intent.EXTRA_TEXT, orderSummary)

                    // Check if there's an app that can handle this intent before launching it)
                    if (context.packageManager?.resolveActivity(intent, 0) != null) {
                        // Start a new activity with the given intent (this may open the share dialog on a
                        // device if multiple apps can handle this intent)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.send).uppercase())
            }
            OutlinedButton(
                onClick = summaryComponent.onCancelClick,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.cancel).uppercase())
            }
        }
    }
}


@Composable
private fun OrderDetail(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title.uppercase(),
            modifier = Modifier,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = value,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.order_summary_margin)),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )
        Divider(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.side_margin))
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}
