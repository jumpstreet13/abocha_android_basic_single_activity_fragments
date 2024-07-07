package com.example.cupcake.presentation.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.Destination
import com.example.cupcake.navigation.getScreenEnterTransition
import com.example.cupcake.navigation.getScreenExitTransition
import com.example.cupcake.presentation.components.AppScreenWrapper
import com.example.cupcake.uikit.AppButton
import com.example.cupcake.uikit.AppSecondaryButton

fun NavGraphBuilder.addSummaryDestination(
    navHostController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    composable<Destination.SummaryScreen>(
        enterTransition = getScreenEnterTransition(),
        exitTransition = getScreenExitTransition()
    ) {
        val context = LocalContext.current
        val numberOfCupcakes = sharedViewModel.quantity.observeAsState(0).value
        val flavor = sharedViewModel.flavor.observeAsState().value.orEmpty()
        val date = sharedViewModel.date.observeAsState().value.orEmpty()
        val price = sharedViewModel.price.observeAsState().value.orEmpty()

        SummaryScreen(
            quantity = numberOfCupcakes.toString(),
            flavor = flavor,
            date = date,
            price = price,
            onBackButtonClick = navHostController::popBackStack,
            onSendOrderButtonClick = {
                context.sendOrderIntent(numberOfCupcakes, flavor, date, price)
            },
            onCancelButtonClick = {
                sharedViewModel.resetOrder()
                navHostController.popBackStack(Destination.StartScreen, false)
            }
        )
    }
}

@Composable
private fun SummaryScreen(
    quantity: String,
    flavor: String,
    date: String,
    price: String,
    onBackButtonClick: () -> Unit,
    onSendOrderButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
) {
    AppScreenWrapper(
        title = stringResource(id = R.string.order_summary),
        onBackButtonClick = onBackButtonClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SummaryItem(
                title = stringResource(id = R.string.quantity),
                value = quantity
            )
            SummaryItem(
                title = stringResource(id = R.string.flavor),
                value = flavor
            )
            SummaryItem(
                title = stringResource(id = R.string.pickup_date),
                value = date
            )
            Text(
                text = stringResource(id = R.string.total_price, price).uppercase(),
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            AppButton(
                text = stringResource(id = R.string.send),
                onClick = onSendOrderButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
            AppSecondaryButton(
                text = stringResource(id = R.string.cancel),
                onClick = onCancelButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SummaryItem(title: String, value: String) {
    Column {
        Text(text = title.uppercase(), fontSize = 16.sp)
        Text(
            text = value,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSummaryScreen() {
    SummaryScreen(
        quantity = "1",
        flavor = "Chocolate",
        date = "Tuesday",
        price = "5$",
        onBackButtonClick = { },
        onSendOrderButtonClick = { },
        onCancelButtonClick = {}
    )
}

private fun Context.sendOrderIntent(
    numberOfCupcakes: Int,
    flavor: String,
    date: String,
    price: String
) {
    val orderSummary = getString(
        R.string.order_details,
        resources.getQuantityString(
            R.plurals.cupcakes,
            numberOfCupcakes,
            numberOfCupcakes
        ),
        flavor,
        date,
        price
    )

    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)

    // Check if there's an app that can handle this intent before launching it
    if (packageManager?.resolveActivity(intent, 0) != null) {
        // Start a new activity with the given intent (this may open the share dialog on a
        // device if multiple apps can handle this intent)
        startActivity(intent)
    }
}