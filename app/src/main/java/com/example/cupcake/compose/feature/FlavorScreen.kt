package com.example.cupcake.compose.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.compose.ui.designsystem.SelectionScreen
import com.example.cupcake.model.OrderViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val FLAVOR_ROUTE = "flavor_route"

fun NavGraphBuilder.flavorScreen(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    composable(route = FLAVOR_ROUTE) {
        FlavorRoute(
            viewModel,
            onCancelOrder,
            onNext)
    }
}

@Composable
fun FlavorRoute(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    val selectedFlavor = viewModel.flavor.observeAsState()
    val subtotalPrice = viewModel.price.observeAsState()

    FlavorScreen(
        selectedFlavor = selectedFlavor,
        subtotalPrice = subtotalPrice,
        onSelect = viewModel::setFlavor,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}

@Composable
private fun FlavorScreen(
    selectedFlavor: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    SelectionScreen(
        items = getFlavors(),
        selectedItem = selectedFlavor,
        subtotalPrice = subtotalPrice,
        onSelect = onSelect,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}


@Composable
@NonRestartableComposable
private fun getFlavors(): ImmutableList<String> {
    return persistentListOf(
        stringResource(id = R.string.vanilla),
        stringResource(id = R.string.chocolate),
        stringResource(id = R.string.red_velvet),
        stringResource(id = R.string.salted_caramel),
        stringResource(id = R.string.coffee)
    )
}
