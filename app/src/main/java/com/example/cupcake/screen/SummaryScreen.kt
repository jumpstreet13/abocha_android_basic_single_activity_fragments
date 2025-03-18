package com.example.cupcake.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
import com.example.cupcake.utils.TotalGroup

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val quantity = viewModel.quantity.observeAsState()
    val flavor = viewModel.flavor.observeAsState()
    val pickupDate = viewModel.date.observeAsState()
    val price = viewModel.price.observeAsState()
    AnimatedVisibility(
        true,
        modifier = modifier
            .fillMaxSize()
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
                    .padding(horizontal = dimensionResource(R.dimen.side_margin))
            ) {
                PartOrder(
                    title = stringResource(R.string.quantity),
                    item = quantity.value?.toString().orEmpty()
                )
                PartOrder(
                    title = stringResource(R.string.flavor),
                    item = flavor.value.orEmpty()
                )
                PartOrder(
                    title = stringResource(R.string.pickup_date),
                    item = pickupDate.value.orEmpty()
                )
                TotalGroup(
                    stringResource(R.string.total_price, price.value.orEmpty()),
                    isTotal = true
                )
                ButtonsGroup(
                    onSendClick = onSendClick,
                    onCancelClick = onCancelClick
                )
            }
        }

    }

}

@Composable
fun ButtonsGroup(
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {

        Button(
            onClick = onSendClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.side_margin)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = LocalColor.current.colorPrimary,
                contentColor = LocalColor.current.colorOnPrimary,
                disabledContainerColor = LocalColor.current.colorSecondary,
                disabledContentColor = LocalColor.current.colorOnSecondary
            )
        ) {
            Text(
                stringResource(R.string.send).uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.margin_between_elements)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                stringResource(R.string.cancel).uppercase(),
                color = AppTheme.colors.colorPrimaryVariant,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Composable
fun PartOrder(
    title: String,
    item: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title.uppercase(),
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.side_margin)),
            style = AppTheme.typography.textAppearanceBody1,
        )

        Text(
            text = item.uppercase(),
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.order_summary_margin)),
            style = AppTheme.typography.textAppearanceBody1,
            fontWeight = FontWeight.Bold
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.side_margin)),
            thickness = 1.dp,
        )
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    CupcakeTheme {
        Surface(color = AppTheme.colors.colorBackground) {
            val viewModel = OrderViewModel()
            SummaryScreen(
                viewModel = viewModel
            )
        }
    }
}