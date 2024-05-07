package com.example.cupcake

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(viewModel: OrderViewModel, navController: NavController) {
    val quantityState by viewModel.quantity.observeAsState()
    val flavorState by viewModel.flavor.observeAsState()
    val dateState by viewModel.date.observeAsState()
    val priceState by viewModel.price.observeAsState()
    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        Text(
            text = stringResource(id = R.string.quantity),
            style = MaterialTheme.typography.body1
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.order_summary_margin)),
            text = quantityState.toString(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.side_margin),
                bottom = dimensionResource(id = R.dimen.side_margin)
            ),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Text(
            text = stringResource(id = R.string.flavor),
            style = MaterialTheme.typography.body1,
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.order_summary_margin)),
            text = flavorState.orEmpty(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.side_margin),
                bottom = dimensionResource(id = R.dimen.side_margin)
            ),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Text(
            text = stringResource(id = R.string.pickup_date),
            style = MaterialTheme.typography.body1
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.order_summary_margin)),
            text = dateState.orEmpty(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.side_margin),
                bottom = dimensionResource(id = R.dimen.margin_between_elements)
            ),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.side_margin)),
                text = stringResource(id = R.string.total_price, priceState.orEmpty()).uppercase(),
                style = MaterialTheme.typography.h6
            )
        }
        Button(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin))
                .fillMaxWidth(),
            onClick = {
                // Construct the order summary text with information from the view model
                val numberOfCupcakes = viewModel.quantity.value ?: 0
                val orderSummary = activity.getString(
                    R.string.order_details,
                    activity.resources.getQuantityString(
                        R.plurals.cupcakes,
                        numberOfCupcakes,
                        numberOfCupcakes
                    ),
                    viewModel.flavor.value.toString(),
                    viewModel.date.value.toString(),
                    viewModel.price.value.toString()
                )

                // Create an ACTION_SEND implicit intent with order details in the intent extras
                val intent = Intent(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.new_cupcake_order))
                    .putExtra(Intent.EXTRA_TEXT, orderSummary)

                // Check if there's an app that can handle this intent before launching it
                if (activity.packageManager?.resolveActivity(intent, 0) != null) {
                    // Start a new activity with the given intent (this may open the share dialog on a
                    // device if multiple apps can handle this intent)
                    activity.startActivity(intent)
                }
            }) {
            Text(text = stringResource(id = R.string.send).uppercase())
        }
        OutlinedButton(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_between_elements))
                .fillMaxWidth(),
            onClick = {
                // Reset order in view model
                viewModel.resetOrder()

                // Navigate back to the [StartFragment] to start over
                navController.popBackStack(route = Screen.Start.name, inclusive = false)
            }) {
            Text(text = stringResource(id = R.string.cancel).uppercase())
        }
    }
}