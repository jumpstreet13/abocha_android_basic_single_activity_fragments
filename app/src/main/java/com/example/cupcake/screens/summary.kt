package com.example.cupcake.screens

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.MainTheme
import com.example.cupcake.theme.PrimaryButton
import com.example.cupcake.theme.SecondaryButton

@Composable
fun SummaryScreen(
    model: OrderViewModel = viewModel()
) {
    val navController = LocalNavigation.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(R.dimen.side_margin))
        ) {
            // order items
            remember {
                listOf(
                    R.string.quantity to model.quantity.value.toString(),
                    R.string.flavor to model.flavor.value.toString(),
                    R.string.pickup_date to model.date.value.toString()
                )
            }.forEach { item ->
                val (strId, value) = item
                Text(
                    text = stringResource(strId).toUpperCase(Locale.current),
                    style = MaterialTheme.typography.body1
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
                Text(
                    text = value,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(R.dimen.side_margin))
                )
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

            // total price
            val totalPrice = model.price.observeAsState("").value
            Text(
                text = stringResource(R.string.total_price, totalPrice)
                    .toUpperCase(Locale.current),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.side_margin))
                    .align(Alignment.End)
            )

            val context = LocalContext.current

            val sendOrder = remember {{
                // Construct the order summary text with information from the view model
                val numberOfCupcakes = model.quantity.value ?: 0
                val orderSummary = context.getString(
                    R.string.order_details,
                    context.resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
                    model.flavor.value.toString(),
                    model.date.value.toString(),
                    model.price.value.toString()
                )
                // Create an ACTION_SEND implicit intent with order details in the intent extras
                val intent = Intent(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.new_cupcake_order))
                    .putExtra(Intent.EXTRA_TEXT, orderSummary)
                // Check if there's an app that can handle this intent before launching it
                if (context.packageManager?.resolveActivity(intent, 0) != null) {
                    // Start a new activity with the given intent (this may open the share dialog on a
                    // device if multiple apps can handle this intent)
                    context.startActivity(intent)
                }
            }}

            // buttons
            PrimaryButton(
                labelId = R.string.send,
                modifier = Modifier.fillMaxWidth(),
                onClick = sendOrder
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))
            // Cancel the order and start over.
            SecondaryButton(
                labelId = R.string.cancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Reset order in view model
                model.resetOrder()
                // Navigate back to start over
                navController.popBackStack(route = Screen.Home.route, inclusive = false)
            }
        }
    }
}

@Preview(
    name = "PickupScreen",
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun PickupPreview() {
    MainTheme {
        CompositionLocalProvider(
            LocalNavigation provides rememberNavController()
        ) {
            SummaryScreen(
                model = viewModel<OrderViewModel>().apply {
                    setQuantity(6)
                    setFlavor(stringResource(R.string.red_velvet))
                    setDate(dateOptions[2])
                }
            )
        }
    }
}
