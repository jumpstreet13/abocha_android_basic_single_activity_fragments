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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.model.OrderViewModel

@Composable
fun FlavorScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navigationState: NavigationState,
) {
    val sideMargin = dimensionResource(R.dimen.side_margin)
    val state = remember { MutableTransitionState(false).apply { targetState = true } }
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
                    stringResource(R.string.choose_flavor),
                    navigationState,
                    Screen.Start.route
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
                FlavorRadioGroup(
                    viewModel = viewModel,
                    sideMargin = sideMargin
                )
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
                ButtonsRaw(
                    viewModel = viewModel,
                    navigationState = navigationState,
                    route = Screen.Pickup.route,
                    sideMargin = sideMargin
                )
            }
        }
    }
}


@Composable
private fun FlavorRadioGroup(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    sideMargin: Dp,
) {
    val radioOptions = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    )
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(radioOptions[0]) }
    Column(modifier = modifier.selectableGroup()) {
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
                            viewModel.setFlavor(text)
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