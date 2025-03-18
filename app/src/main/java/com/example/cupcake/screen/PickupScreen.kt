package com.example.cupcake.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.AppTheme
import com.example.cupcake.theme.CupcakeTheme
import com.example.cupcake.utils.CupcakeAppBar
import com.example.cupcake.utils.NavigateButtonsGroup
import com.example.cupcake.utils.RadioGroupOptions
import com.example.cupcake.utils.TotalGroup

@Composable
fun PickupScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onDateClick: (String) -> Unit = {},
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val dateOptions = viewModel.dateOptions
    val selectedDate = viewModel.date.observeAsState()
    val price = viewModel.price.observeAsState()
    AnimatedVisibility(
        true,
        modifier = modifier
    ) {
        Column {
            CupcakeAppBar(
                title = stringResource(R.string.choose_pickup_date),
                modifier = Modifier,
                isVisibleBackIcon =true,
                onBackClick = onBackClick,
            )

            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.side_margin))
            ) {
                RadioGroupOptions(
                    options = dateOptions,
                    optionSelected = selectedDate.value.orEmpty(),
                    onclick = onDateClick
                )
                TotalGroup(stringResource(R.string.subtotal_price, price.value.orEmpty()))
                NavigateButtonsGroup(
                    onCancelClick = onCancelClick,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@Preview
@Composable
fun PickupScreenPreview(modifier: Modifier = Modifier) {
    CupcakeTheme {
        Surface(color = AppTheme.colors.colorBackground) {
            val viewModel = OrderViewModel()
            PickupScreen(
                viewModel = viewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}