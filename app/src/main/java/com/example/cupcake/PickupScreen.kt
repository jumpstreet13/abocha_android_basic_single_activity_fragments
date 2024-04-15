package com.example.cupcake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.ButtonPink

@Composable
fun PickupScreen(navController: NavHostController, sharedViewModel: OrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        val priceText = mutableStateOf("${sharedViewModel.price.value}")
        MultipleRadioButtons(sharedViewModel, "pickupDate", sharedViewModel.dateOptions, priceText)
        Text(
            text = "${stringResource(R.string.subtotal_price)} ${priceText.value}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { cancelOrder(navController, sharedViewModel) },
                modifier = Modifier.fillMaxWidth(0.5F),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                onClick = { goToNextScreen(navController, NavScreen.SummaryScreenNav.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}
