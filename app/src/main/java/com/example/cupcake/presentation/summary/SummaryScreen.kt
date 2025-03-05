package com.example.cupcake.presentation.summary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.ComposeSampleAppTheme
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.theme.LocalTypography
import com.example.cupcake.utils.AppBarCustom
import com.example.cupcake.utils.Total

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickSend: (String) -> Unit,
) {

    val scrollState = rememberScrollState()
    val quantity = viewModel.quantity.observeAsState()
    val flavor = viewModel.flavor.observeAsState()
    val date = viewModel.date.observeAsState()
    val price = viewModel.price.observeAsState()
    val context = LocalContext.current
    var orderSummary = remember { "" }

    LaunchedEffect(quantity, flavor, date, price) {
        val numberOfCupcakes = quantity.value ?: 0
        orderSummary = context.getString(
            R.string.order_details,
            context.resources.getQuantityString(
                R.plurals.cupcakes,
                numberOfCupcakes,
                numberOfCupcakes
            ),
            flavor.value.toString(),
            date.value.toString(),
            price.value.toString()
        )
    }

    AnimatedVisibility(true) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.side_margin))
        ) {
            AppBarCustom(title = stringResource(R.string.order_summary)) { onClickBack.invoke() }
            TwoTextSummary(
                title = stringResource(R.string.quantity),
                subtitle = quantity.value?.toString().orEmpty(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            TwoTextSummary(
                title = stringResource(R.string.flavor),
                subtitle = flavor.value.orEmpty(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            TwoTextSummary(
                title = stringResource(R.string.pickup_date),
                subtitle = date.value.orEmpty(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            Total(
                price.value.orEmpty(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin))
            )
            SendOtherAppButton(
                onClick = {
                    onClickSend.invoke(orderSummary)
                }
            )
            CancelButton(
                onClick = {
                    viewModel.resetOrder()
                    onClickCancel.invoke()
                }
            )
        }
    }
}

@Composable
private fun TwoTextSummary(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Text(
            color = LocalColor.current.textColor,
            text = title.uppercase(),
            style = LocalTypography.current.textAppearanceSubtitle16
        )
        Text(
            color = LocalColor.current.textColor,
            text = subtitle,
            style = LocalTypography.current.textAppearanceSubtitle16Bold,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.order_summary_margin))
        )
        HorizontalDivider(modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_between_elements)))
    }
}

@Composable
private fun SendOtherAppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonColors(
            containerColor = LocalColor.current.colorPrimary,
            contentColor = LocalColor.current.colorOnPrimary,
            disabledContainerColor = LocalColor.current.colorSecondary,
            disabledContentColor = LocalColor.current.colorOnSecondary
        ),
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.send))
    }
}

@Composable
private fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        border = BorderStroke(2.dp, LocalColor.current.colorButtonBorder),
        colors = ButtonColors(
            containerColor = LocalColor.current.colorOnPrimary,
            contentColor = LocalColor.current.colorPrimary,
            disabledContainerColor = LocalColor.current.colorOnSecondary,
            disabledContentColor = LocalColor.current.colorSecondary
        ),
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.cancel))
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    ComposeSampleAppTheme/*(darkTheme = true)*/ {
        SummaryScreen(
            viewModel(),
            Modifier.background(LocalColor.current.colorOnPrimary)
        ) {}
    }
}