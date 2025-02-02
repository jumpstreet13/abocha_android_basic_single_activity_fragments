package com.example.cupcake

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.example.cupcake.model.OrderViewModel

@Composable
fun ButtonsRaw(modifier: Modifier = Modifier, viewModel: OrderViewModel, navigationState: NavigationState, route: String, sideMargin: Dp) {
    Row(modifier = modifier) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { cancelOrder(viewModel, navigationState) }
        ) {
            Text(
                text = stringResource(R.string.cancel).uppercase(),
                letterSpacing = TextUnit(1f, TextUnitType.Sp)
            )

        }
        Spacer(modifier = Modifier.width(sideMargin))
        Button(
            modifier = Modifier.weight(1f),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { goToNextScreen(navigationState, route) }
        ) {
            Text(
                text = stringResource(R.string.next).uppercase(),
                letterSpacing = TextUnit(1f, TextUnitType.Sp)
            )
        }
    }
}
fun cancelOrder(viewModel: OrderViewModel, navigationState: NavigationState) {
    // Reset order in view model
    viewModel.resetOrder()
    navigationState.navigateTo(Screen.Start.route)
}

fun goToNextScreen(navigationState: NavigationState, route: String) {
    // Navigate to the next destination
    navigationState.navigateTo(route)
}