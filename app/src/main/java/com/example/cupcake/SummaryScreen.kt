package com.example.cupcake

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.ButtonPink

@Composable
fun SummaryScreen(navController: NavHostController, sharedViewModel: OrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.quantity).uppercase()
            )
            Text(
                text = "${sharedViewModel.quantity.value} cupcakes"
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.margin_between_elements))
                .width(1.dp),
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.margin_between_elements))
        ) {
            Text(
                text = stringResource(R.string.flavor).uppercase()
            )
            Text(
                text = "${sharedViewModel.flavor.value}"
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.margin_between_elements))
                .width(1.dp),
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.pickup_date).uppercase()
            )
            Text(
                text = "${sharedViewModel.date.value}"
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.margin_between_elements))
                .width(1.dp),
            color = Color.Gray
        )
        Text(
            text = "${stringResource(R.string.total_price).uppercase()} $${sharedViewModel.price.value}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
        Button(
            onClick = { sendOrder(sharedViewModel) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
        Button(
            onClick = { cancelOrder(navController, sharedViewModel) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = ButtonPink
            ),
            border = BorderStroke(1.dp, Color.DarkGray),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

    }
}

fun sendOrder(sharedViewModel: OrderViewModel) {
    // Construct the order summary text with information from the view model
    val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
    val orderSummary = MainActivity.appContext.getString(
        R.string.order_details,
        MainActivity.appContext.resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        sharedViewModel.flavor.value.toString(),
        sharedViewModel.date.value.toString(),
        sharedViewModel.price.value.toString()
    )

    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, MainActivity.appContext.getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    // Check if there's an app that can handle this intent before launching it
    if (MainActivity.appContext.packageManager?.resolveActivity(intent, 0) != null) {
        // Start a new activity with the given intent (this may open the share dialog on a
        // device if multiple apps can handle this intent)
        startActivity(MainActivity.appContext, intent, null)
    }
}
