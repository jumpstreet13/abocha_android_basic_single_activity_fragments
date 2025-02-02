package com.example.cupcake

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.model.OrderViewModel


@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navigationState: NavigationState,
    sendOrder: (String) -> Unit,
) {
    val sideMargin = dimensionResource(R.dimen.side_margin)
    val state = remember { MutableTransitionState(false).apply { targetState = true } }

    val numberOfCupcakes = viewModel.quantity.value ?: 0
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavor.value.toString(),
        viewModel.date.value.toString(),
        viewModel.price.value.toString()
    )

    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visibleState = state,
        enter = slideInHorizontally(animationSpec = tween(300)) { with(density) { 150.dp.roundToPx() } }
                + fadeIn(animationSpec = tween(150)),
        exit = ExitTransition.None
    ) {
        Scaffold(
            topBar = {
                TopAppBarWithArrow(
                    stringResource(R.string.order_summary),
                    navigationState,
                    Screen.Pickup.route
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(sideMargin),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OrderDetails(
                    viewModel = viewModel,
                    sideMargin = sideMargin
                )
                Spacer(modifier = Modifier.height(sideMargin))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { sendOrder(orderSummary) }
                ) {
                    Text(
                        text = stringResource(R.string.send).uppercase(),
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )

                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { cancelOrder(viewModel, navigationState) }
                ) {
                    Text(
                        text = stringResource(R.string.cancel).uppercase(),
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TextBlock(
    modifier: Modifier = Modifier,
    headerText: String,
    bodyText: String,
    sideMargin: Dp,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = headerText.uppercase(),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = bodyText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(sideMargin))
        HorizontalDivider()
    }
}

@Composable
fun OrderDetails(modifier: Modifier = Modifier, viewModel: OrderViewModel, sideMargin: Dp) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sideMargin)
    ) {
        TextBlock(
            headerText = stringResource(R.string.quantity),
            bodyText = viewModel.quantity.value.toString(),
            sideMargin = sideMargin
        )
        TextBlock(
            headerText = stringResource(R.string.flavor),
            bodyText = viewModel.flavor.value.toString(),
            sideMargin = sideMargin
        )
        TextBlock(
            headerText = stringResource(R.string.pickup_date),
            bodyText = viewModel.date.value.toString(),
            sideMargin = sideMargin
        )
        Text(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(
                R.string.total_price,
                viewModel.price.value ?: "0.0"
            ).uppercase(),
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 18.sp
        )
    }
}