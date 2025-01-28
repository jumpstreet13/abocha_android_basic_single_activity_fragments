package com.example.cupcake

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.model.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupScreen(viewModel: OrderViewModel, navigationState: NavigationState) {
    val sideMargin = dimensionResource(R.dimen.side_margin)
    val state = remember { MutableTransitionState(false).apply { targetState = true } }

    fun cancelOrder() {
        // Reset order in view model
        viewModel.resetOrder()
        navigationState.navigateTo(Screen.Start.route)
    }

    fun goToNextScreen() {
        // Navigate to the next destination
        navigationState.navigateTo(Screen.Summary.route)
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
                            text = stringResource(R.string.choose_pickup_date)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigationState.navigateTo(Screen.Flavor.route, true) }
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
                PickupRadioGroup(viewModel, sideMargin)
                Spacer(modifier = Modifier.height(sideMargin))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(sideMargin))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(R.string.subtotal_price, viewModel.price.value ?: "0.0"),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(sideMargin))
                Row {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = MaterialTheme.shapes.extraSmall,
                        onClick = { cancelOrder() }
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
                        onClick = { goToNextScreen() }
                    ) {
                        Text(
                            text = stringResource(R.string.next).uppercase(),
                            letterSpacing = TextUnit(1f, TextUnitType.Sp)
                        )

                    }
                }
            }
        }
    }
}


@Composable
fun PickupRadioGroup(viewModel: OrderViewModel, sideMargin: Dp) {
    val radioOptions = viewModel.dateOptions
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Column(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        role = Role.RadioButton,
                        onClick = {
                            onOptionSelected(text)
                            viewModel.setDate(text)
                        }
                    )
                    .padding(horizontal = sideMargin),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = sideMargin)
                )
            }
        }
    }
}