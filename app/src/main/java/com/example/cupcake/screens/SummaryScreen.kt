package com.example.cupcake.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cupcake.R
import com.example.cupcake.Start
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TitleAndValue(
                    modifier = Modifier,
                    stringResource(R.string.quantity),
                    viewModel.quantity.value.toString(),
                    showHorizontalDivider = true
                )
                TitleAndValue(
                    modifier = Modifier,
                    stringResource(R.string.flavor),
                    viewModel.flavor.value.toString(),
                    showHorizontalDivider = true
                )
                TitleAndValue(
                    modifier = Modifier,
                    stringResource(R.string.pickup_date),
                    viewModel.date.value.toString(),
                    showHorizontalDivider = true
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .align(Alignment.End),
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    text = stringResource(R.string.total_price, viewModel.price.value.toString()).uppercase(),
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Construct the order summary text with information from the view model
                    val numberOfCupcakes = viewModel.quantity.value ?: 0
                    val orderSummary = stringResource(
                        R.string.order_details,
                        pluralStringResource(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
                        viewModel.flavor.value.toString(),
                        viewModel.date.value.toString(),
                        viewModel.price.value.toString()
                    )

                    // Create an ACTION_SEND implicit intent with order details in the intent extras
                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT, stringResource(R.string.new_cupcake_order))
                        .putExtra(Intent.EXTRA_TEXT, orderSummary)

                    // Check if there's an app that can handle this intent before launching it
                    val activity = LocalContext.current as AppCompatActivity

                    CupcakeButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.send)
                    ) {
                        if (activity.packageManager?.resolveActivity(intent, 0) != null) {
                            // Start a new activity with the given intent (this may open the share dialog on a
                            // device if multiple apps can handle this intent)
                            activity.startActivity(intent)
                        }
                    }

                    CupcakeButton(
                        modifier = Modifier.fillMaxWidth(),
                        isWhite = true,
                        text = stringResource(R.string.cancel)
                    ) {
                        viewModel.resetOrder()
                        navController.popBackStack(Start, false)
                    }
                }
            }
        }
    }
}

@Composable
fun TitleAndValue(modifier: Modifier, title: String, value: String, showHorizontalDivider: Boolean = false) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = title.uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        if (showHorizontalDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
        }
    }
}