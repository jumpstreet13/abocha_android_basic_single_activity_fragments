package com.example.cupcake

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.model.OrderViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(viewModel: OrderViewModel, navigationState: NavigationState) {
    val imageSize = dimensionResource(R.dimen.image_size)
    val sideMargin = dimensionResource(R.dimen.side_margin)
    val marginBetweenElements = dimensionResource(R.dimen.margin_between_elements)
    val buttonWidth = dimensionResource(R.dimen.order_cupcake_button_width)
    val defaultFlavor = stringResource(R.string.vanilla)
    val state = remember { MutableTransitionState(false).apply { targetState = true } }

    fun orderCupCake(quantity: Int) {
        // Update the view model with the quantity
        viewModel.setQuantity(quantity)

        // If no flavor is set in the view model yet, select vanilla as default flavor
        if (viewModel.hasNoFlavorSet()) {
            viewModel.setFlavor(defaultFlavor)
        }

        // Navigate to the next destination to select the flavor of the cupcakes
        navigationState.navigateTo(Screen.Flavor.route)
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
                            text = stringResource(R.string.app_name)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                Spacer(modifier = Modifier.height(marginBetweenElements))
                Image(
                    modifier = Modifier
                        .size(imageSize),
                    painter = painterResource(R.drawable.cupcake),
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
                Text(
                    modifier = Modifier.padding(bottom = sideMargin),
                    text = stringResource(R.string.order_cupcakes),
                    color = colorResource(R.color.material_on_background_emphasis_medium),
                    fontSize = 34.sp
                )
                Spacer(modifier = Modifier.height(marginBetweenElements))
                Button(modifier = Modifier
                    .width(buttonWidth),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { orderCupCake(1) }) {
                    Text(
                        text = stringResource(R.string.one_cupcake).uppercase(),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(marginBetweenElements))
                Button(modifier = Modifier
                    .width(buttonWidth),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { orderCupCake(6) }) {
                    Text(
                        text = stringResource(R.string.six_cupcakes).uppercase(),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(marginBetweenElements))
                Button(modifier = Modifier
                    .width(buttonWidth),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { orderCupCake(12) }) {
                    Text(
                        text = stringResource(R.string.twelve_cupcakes).uppercase(),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

