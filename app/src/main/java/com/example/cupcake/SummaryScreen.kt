package com.example.cupcake

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    navigationState: NavigationState,
    activity: MainActivity,
) {
    val sideMargin = dimensionResource(R.dimen.side_margin)
    val numberOfCupcakes = viewModel.quantity.value ?: 0
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavor.value.toString(),
        viewModel.date.value.toString(),
        viewModel.price.value.toString()
    )
    val orderSummaryHeader = stringResource(R.string.new_cupcake_order)
    val state = remember { MutableTransitionState(false).apply { targetState = true } }

    fun sendOrder() {
        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, orderSummaryHeader)
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (activity.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            activity.startActivity(intent)
        }
    }

    fun cancelOrder() {
        // Reset order in view model
        viewModel.resetOrder()
        navigationState.navigateTo(Screen.Start.route)
    }

    val density = LocalDensity.current
    AnimatedVisibility(
        visibleState = state,
        enter = slideInHorizontally(animationSpec = tween(300)) { with(density) { 150.dp.roundToPx() } }
                + fadeIn(animationSpec = tween(150)),
        exit = ExitTransition.None
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.order_summary)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigationState.navigateTo(Screen.Pickup.route, true) }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
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
                TextBlock(
                    stringResource(R.string.quantity),
                    viewModel.quantity.value.toString(),
                    this,
                    sideMargin
                )
                Spacer(modifier = Modifier.height(sideMargin))
                TextBlock(
                    stringResource(R.string.flavor),
                    viewModel.flavor.value.toString(),
                    this,
                    sideMargin
                )
                Spacer(modifier = Modifier.height(sideMargin))
                TextBlock(
                    stringResource(R.string.pickup_date),
                    viewModel.date.value.toString(),
                    this,
                    sideMargin
                )
                Spacer(modifier = Modifier.height(sideMargin))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(
                        R.string.total_price,
                        viewModel.price.value ?: "0.0"
                    ).uppercase(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(sideMargin))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { sendOrder() }
                ) {
                    Text(
                        text = stringResource(R.string.send).uppercase(),
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )

                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { cancelOrder() }
                ) {
                    Text(
                        text = stringResource(R.string.cancel).uppercase(),
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                }
                Spacer(modifier = Modifier.width(sideMargin))
            }
        }
    }
}

@Composable
fun TextBlock(headerText: String, bodyText: String, scope: ColumnScope, sideMargin: Dp) {
    scope.apply {
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