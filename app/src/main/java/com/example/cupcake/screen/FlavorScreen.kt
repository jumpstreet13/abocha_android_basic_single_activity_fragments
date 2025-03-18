package com.example.cupcake.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.AppTheme
import com.example.cupcake.theme.CupcakeTheme
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.utils.CupcakeAppBar
import com.example.cupcake.utils.NavigateButtonsGroup
import com.example.cupcake.utils.RadioGroupOptions
import com.example.cupcake.utils.TotalGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onFlavorClick: (String) -> Unit = {},
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val price = viewModel.price.observeAsState()
    val flavor = viewModel.flavor.observeAsState()
    if (viewModel.hasNoFlavorSet())
        viewModel.setFlavor(stringResource(R.string.vanilla))
    val options = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    )
    AnimatedVisibility(
        true,
        modifier
    ) {
        Column (modifier = Modifier) {
            CupcakeAppBar(
                title = stringResource(R.string.choose_flavor),
                isVisibleBackIcon = true,
                onBackClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.side_margin))
            ) {
                RadioGroupOptions(
                    options = options,
                    optionSelected = flavor.value.orEmpty(),
                    onclick = onFlavorClick
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
fun FlavorScreenPreview() {
    CupcakeTheme {
        Surface(color = AppTheme.colors.colorBackground) {
            val viewModel = OrderViewModel()
            FlavorScreen(
                viewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}