package com.example.cupcake.presentation.flavor

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
import androidx.compose.ui.platform.LocalContext
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
fun FlavorScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onClickNext: () -> Unit = {}
) {

    val scrollState = rememberScrollState()
    val price = viewModel.price.observeAsState()

    if (viewModel.hasNoFlavorSet())
        viewModel.setFlavor(LocalContext.current.getString(R.string.vanilla))


    AnimatedVisibility(true) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.side_margin))
        ) {
            AppBarCustom(title = stringResource(R.string.flavor)) { onClickBack.invoke() }

            RadioGroupFlavor(
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
                    onClickBack.invoke()
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
private fun RadioGroupFlavor(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier
) {

    val flavor = viewModel.flavor.observeAsState()

    Column(
        modifier = modifier
    ) {
        RadioButtonWithTitle(
            flavorSelected = flavor.value,
            title = stringResource(R.string.vanilla)
        ) { viewModel.setFlavor(it) }
        RadioButtonWithTitle(
            flavorSelected = flavor.value,
            title = stringResource(R.string.chocolate)
        ) { viewModel.setFlavor(it) }
        RadioButtonWithTitle(
            flavorSelected = flavor.value,
            title = stringResource(R.string.red_velvet)
        ) { viewModel.setFlavor(it) }
        RadioButtonWithTitle(
            flavorSelected = flavor.value,
            title = stringResource(R.string.salted_caramel)
        ) { viewModel.setFlavor(it) }
        RadioButtonWithTitle(
            flavorSelected = flavor.value,
            title = stringResource(R.string.coffee)
        ) { viewModel.setFlavor(it) }
    }
}

@Preview
@Composable
fun FlavorScreenPreview() {
    ComposeSampleAppTheme/*(darkTheme = true)*/ {
        FlavorScreen(
            viewModel(),
            Modifier.background(LocalColor.current.colorOnPrimary)
        )
    }
}