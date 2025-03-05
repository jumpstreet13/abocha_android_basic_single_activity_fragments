package com.example.cupcake.presentation.pickup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.ComposeSampleAppTheme
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.utils.AppBarCustom
import com.example.cupcake.utils.BottomRowButtons
import com.example.cupcake.utils.RadioButtonWithTitle
import com.example.cupcake.utils.Subtotal

@Composable
fun PickupScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickNext: () -> Unit = {}
) {

    val scrollState = rememberScrollState()
    val price = viewModel.price.observeAsState()

    AnimatedVisibility(true) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.side_margin))
        ) {
            AppBarCustom(title = stringResource(R.string.choose_pickup_date)) { onClickBack.invoke() }
            RadioGroupPickup(
                viewModel = viewModel,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            HorizontalDivider(modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin)))
            Subtotal(
                price = price.value.orEmpty(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            BottomRowButtons(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_between_elements)),
                onClickCancel = {
                    onClickCancel.invoke()
                    viewModel.resetOrder()
                },
                onClickNext = {
                    onClickNext.invoke()
                }
            )
        }
    }
}

@Composable
private fun RadioGroupPickup(
    viewModel: OrderViewModel,
    modifier: Modifier= Modifier
){

    val date = viewModel.date.observeAsState()

    Column(
        modifier = modifier
    ) {
        RadioButtonWithTitle(
            flavorSelected = date.value,
            title = viewModel.dateOptions[0]
        ) { viewModel.setDate(it) }
        RadioButtonWithTitle(
            flavorSelected = date.value,
            title = viewModel.dateOptions[1]
        ) { viewModel.setDate(it) }
        RadioButtonWithTitle(
            flavorSelected = date.value,
            title = viewModel.dateOptions[2]
        ) { viewModel.setDate(it) }
        RadioButtonWithTitle(
            flavorSelected = date.value,
            title = viewModel.dateOptions[3]
        ) { viewModel.setDate(it) }
    }
}

@Preview
@Composable
fun PickupScreenPreview() {
    ComposeSampleAppTheme/*(darkTheme = true)*/ {
        PickupScreen(
            viewModel(),
            Modifier.background(LocalColor.current.colorOnPrimary)
        )
    }
}