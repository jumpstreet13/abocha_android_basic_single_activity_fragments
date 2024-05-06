package com.example.cupcake

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
            onClick = { sharedViewModel.sendOrder() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
        ) {
            Text(text = stringResource(id = R.string.send))
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
