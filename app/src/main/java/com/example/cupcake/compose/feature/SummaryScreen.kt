package com.example.cupcake.compose.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.compose.ui.designsystem.CupcakeButton
import com.example.cupcake.compose.ui.designsystem.CupcakeOutlinedButton
import com.example.cupcake.compose.ui.util.MARGIN_BETWEEN_ELEMENTS
import com.example.cupcake.compose.ui.util.ORDER_SUMMARY_MARGIN
import com.example.cupcake.compose.ui.util.SIDE_MARGIN
import com.example.cupcake.model.OrderViewModel

const val SUMMARY_ROUTE = "summary_route"

fun NavGraphBuilder.summaryRoute(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onSendOrder: () -> Unit
) {
    composable(route = SUMMARY_ROUTE) {
        SummaryRoute(
            viewModel,
            onCancelOrder,
            onSendOrder)
    }
}

@Composable
fun SummaryRoute(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onSendOrder: () -> Unit
) {
    val quantity = viewModel.quantity.observeAsState()
    val flavor = viewModel.flavor.observeAsState()
    val date = viewModel.date.observeAsState()
    val price = viewModel.price.observeAsState()

    SummaryScreen(
        quantity = quantity,
        flavor = flavor,
        pickupDate = date,
        totalPrice = price,
        onCancelOrder = onCancelOrder,
        onSendOrder = onSendOrder
    )
}

@Composable
private fun SummaryScreen(
    quantity: State<Int?>,
    flavor: State<String?>,
    pickupDate: State<String?>,
    totalPrice: State<String?>,
    onCancelOrder: () -> Unit,
    onSendOrder: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIDE_MARGIN)
    ) {
        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.quantity),
            orderItemText = pluralStringResource(
                id = R.plurals.cupcakes,
                count = quantity.value ?: 0,
                quantity.value ?: 0
            ),
        )

        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.flavor),
            orderItemText = flavor.value.orEmpty(),
        )

        OrderOptionItem(
            orderItemTitle = stringResource(id = R.string.pickup_date),
            orderItemText = pickupDate.value.orEmpty(),
        )

        Spacer(modifier = Modifier.height(MARGIN_BETWEEN_ELEMENTS))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(
                id = R.string.total_price,
                totalPrice.value.orEmpty()
            ).uppercase(),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(SIDE_MARGIN))

        CupcakeButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.send),
            onButtonClick = onSendOrder
        )

        Spacer(modifier = Modifier.height(MARGIN_BETWEEN_ELEMENTS))

        CupcakeOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.cancel),
            onButtonClick = onCancelOrder
        )
    }
}

@Composable
private fun OrderOptionItem(
    orderItemTitle: String,
    orderItemText: String,
) {
    Column {

        Spacer(modifier = Modifier.height(ORDER_SUMMARY_MARGIN))

        Text(
            text = orderItemTitle.uppercase(),
            style = MaterialTheme.typography.body1
        )

        Text(
            text = orderItemText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SIDE_MARGIN)
        )
    }
}