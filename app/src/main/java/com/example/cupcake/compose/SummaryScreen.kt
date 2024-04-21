package com.example.cupcake.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupCakeTheme
import com.example.cupcake.ui.theme.DividerHorizontal
import com.example.cupcake.ui.theme.Paddings

@Composable
fun SummaryScreen(
    navController: NavHostController,
    viewModel: OrderViewModel,
    onSendOrderClick: (String) -> Unit
) {
    val numberOfCupcakes = viewModel.quantity.value ?: 0
    val quantityOfCupcakes =
        pluralStringResource(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes)
    val flavor = viewModel.flavor.value.toString()
    val price = viewModel.price.value.toString()
    val date = viewModel.date.value.toString()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(stringResource(id = R.string.order_summary)) { navController.popBackStack() } },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(Paddings.medium)
            ) {

                OrderDetail(R.string.quantity, quantityOfCupcakes)
                OrderDetail(R.string.flavor, flavor)
                OrderDetail(R.string.pickup_date, date)

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(id = R.string.total_price, price),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                val orderSummary = stringResource(
                    R.string.order_details,
                    quantityOfCupcakes,
                    flavor,
                    date,
                    price
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Paddings.medium),
                    onClick = { onSendOrderClick(orderSummary) },
                    border = BorderStroke(1.dp, DividerHorizontal),
                    shape = RoundedCornerShape(topStart = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.send),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Paddings.xsmall),
                    onClick = {
                        viewModel.resetOrder()
                        navController.navigate(Navigation.START_SCREEN.route)
                    },
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = BorderStroke(1.dp, DividerHorizontal),
                    shape = RoundedCornerShape(topStart = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    )
}


@Composable
private fun OrderDetail(@StringRes titleText: Int, quantityOfCupcakes: String) {
    Column {
        Text(
            text = stringResource(id = titleText),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = quantityOfCupcakes,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                top = Paddings.medium,
                bottom = Paddings.medium
            ),
            thickness = 1.dp,
            color = DividerHorizontal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryFlavorScreen() {
    CupCakeTheme {
        SummaryScreen(
            navController = rememberNavController(),
            viewModel = viewModel(),
            {}
        )
    }
}