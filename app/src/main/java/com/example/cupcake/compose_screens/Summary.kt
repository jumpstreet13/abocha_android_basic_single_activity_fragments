package com.example.cupcake.compose_screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cupcake.OrderState
import com.example.cupcake.R
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("ResourceType")
@Composable
fun Summary(
    orderFlow: StateFlow<OrderState>,
    cancelButtonNavigate: () -> Unit
) {
    val order by orderFlow.collectAsState()
    val context = LocalContext.current
    val extraSubjectString = stringResource(R.string.new_cupcake_order)
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(R.plurals.cupcakes, order.amount, order.amount),
        order.additionalIngredient.toString(),
        order.date.toString(),
        order.price.toString()
    )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.side_margin))
    ) {
        OrderOption(
            optionName = stringResource(R.string.quantity),
            optionValue = order.amount.toString(),
            bottomPadding = dimensionResource(R.dimen.side_margin)
        )
        order.additionalIngredient?.let { ingredient ->
            OrderOption(
                optionName = stringResource(R.string.flavor),
                optionValue = ingredient,
                bottomPadding = dimensionResource(R.dimen.side_margin)
            )
        }
        order.date?.let { date ->
            OrderOption(
                optionName = stringResource(R.string.pickup_date),
                optionValue = date,
                bottomPadding = dimensionResource(R.dimen.margin_between_elements)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.side_margin))
                .align(Alignment.End),
            text = "TOTAL $${order.price}",
            style = MaterialTheme.typography.h6
        )
        Button(
            onClick = {
                sendOrder(context, orderSummary, extraSubjectString)
            },
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.side_margin))
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.send))
        }
        Button(
            onClick = cancelButtonNavigate,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.margin_between_elements))
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.cancel))
        }
    }
}

@Composable
fun OrderOption(
    optionName: String,
    optionValue: String,
    bottomPadding: Dp
) {
    Text(
        text = optionName.uppercase(),
        style = MaterialTheme.typography.h6
    )
    Text(
        text = optionValue,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.order_summary_margin))
    )
    HorizontalDivider(
        modifier = Modifier.padding(
            top = dimensionResource(R.dimen.side_margin),
            bottom = bottomPadding
        ),
        color = Color.Gray,
        thickness = 1.dp
    )
}

@SuppressLint("ResourceType", "StateFlowValueCalledInComposition")
fun sendOrder(context: Context, orderSummary: String, extraSubjectString: String) {
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, extraSubjectString)
        .putExtra(Intent.EXTRA_TEXT, orderSummary)

    if ((context as? Activity)?.packageManager?.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    }
}