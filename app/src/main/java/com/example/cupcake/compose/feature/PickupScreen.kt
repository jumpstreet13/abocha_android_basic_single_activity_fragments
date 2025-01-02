package com.example.cupcake.compose.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cupcake.compose.ui.designsystem.SelectionScreen
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.utils.Route
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun NavGraphBuilder.pickupScreen(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    composable(route = Route.PICKUP.toString()) {
        PickupRoute(
            viewModel,
            onCancelOrder,
            onNext)
    }
}

@Composable
fun PickupRoute(
    viewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    val dates = remember { viewModel.dateOptions.toImmutableList() }
    val selectedDay = viewModel.date.observeAsState()
    val subtotalPrice = viewModel.price.observeAsState()

    PickupScreen(
        dates = dates,
        selectedDay = selectedDay,
        subtotalPrice = subtotalPrice,
        onSelect = viewModel::setDate,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}

@Composable
fun PickupScreen(
    dates: ImmutableList<String>,
    selectedDay: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    SelectionScreen(
        items = dates,
        selectedItem = selectedDay,
        subtotalPrice = subtotalPrice,
        onSelect = onSelect,
        onCancelOrder = onCancelOrder,
        onNext = onNext
    )
}