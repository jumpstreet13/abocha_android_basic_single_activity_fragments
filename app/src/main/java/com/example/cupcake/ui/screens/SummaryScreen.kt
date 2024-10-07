package com.example.cupcake.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.widgets.CupcakeDivider
import com.example.cupcake.ui.widgets.CupcakeTopBar
import com.example.cupcake.ui.widgets.RectangularFilledButton

@Composable
fun SummaryScreen(
    sharedViewModel: OrderViewModel,
    onNavigateUp: () -> Unit,
    onNavigateNext: () -> Unit,
    onNavigateToStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CupcakeTopBar(
                title = stringResource(id = R.string.choose_flavor),
                showUpArrow = true,
                onNavigateUp = onNavigateUp
            )
        }
    )
    { paddingValues ->
        SummaryScreenContent(
            sharedViewModel = sharedViewModel,
            onNavigateToPickupScreen = onNavigateNext,
            onNavigateUp = onNavigateToStart,
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SummaryScreenContent(
    sharedViewModel: OrderViewModel,
    onNavigateToPickupScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {

        OrderResult(
            name = stringResource(R.string.quantity),
            value = sharedViewModel.quantity.value.toString(),
        )

        OrderResult(
            name = stringResource(R.string.flavor),
            value = sharedViewModel.flavor.value.toString(),
        )

        OrderResult(
            name = stringResource(R.string.pickup_date),
            value = sharedViewModel.date.value.toString(),
        )

        Text(
            text = stringResource(id = R.string.total_price, sharedViewModel.price.value.toString()).uppercase(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp).align(alignment = Alignment.End)
        )


        val context = LocalContext.current
        RectangularFilledButton(
            buttonText = stringResource(R.string.send).uppercase(),
            onClick = { sendOrder(context, sharedViewModel) },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        )

        OutlinedButton(
            onClick = onNavigateUp,
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel).uppercase(),
            )
        }
        CancelOrderButton(sharedViewModel, onNavigateToStart)
    }
}

@Composable
private fun OrderResult(name: String, value: String) {
    Text(text = name.uppercase(), fontSize = 18.sp)
    Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
    CupcakeDivider()
}

/**
 * Submit the order by sharing out the order details to another app via an implicit intent.
 */
private fun sendOrder(context: Context, sharedViewModel: OrderViewModel) {
    val orderSummary = makeOrderSummary(sharedViewModel, context)
    val intent = makeImplicitSendOrderIntent(context, orderSummary)

    if (context.packageManager.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    }
}

private fun makeImplicitSendOrderIntent(context: Context, orderSummary: String) = Intent(Intent.ACTION_SEND)
    .setType("text/plain")
    .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.new_cupcake_order))
    .putExtra(Intent.EXTRA_TEXT, orderSummary)

private fun makeOrderSummary(
    sharedViewModel: OrderViewModel,
    context: Context
): String {
    val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
    val orderSummary = context.getString(
        R.string.order_details,
        context.resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        sharedViewModel.flavor.value.toString(),
        sharedViewModel.date.value.toString(),
        sharedViewModel.price.value.toString()
    )
    return orderSummary
}

@Composable
private fun CancelOrderButton(sharedViewModel: OrderViewModel, onNavigateToStart: () -> Unit) {
    OutlinedButton(
        onClick = {
            sharedViewModel.resetOrder()
            onNavigateToStart()
        },
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.cancel).uppercase(),
        )
    }
}

