package com.example.cupcake.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.appTheme.LocalColors
import com.example.cupcake.appTheme.LocalTypography

@Preview
@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    onOptionSelected: (String) -> Unit = {},
    onSendClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    quantity: Int = 6,
    flavor: String = "Chocolate",
    pickupDate: String = "Sunday",
    price: String = "$0"
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.Start

    ) {

        SummaryItem(
            modifier = modifier,
            title = stringResource(R.string.quantity),
            subtitle = pluralStringResource(R.plurals.cupcakes, quantity, quantity)
        )

        SummaryItem(
            modifier = modifier,
            title = stringResource(R.string.flavor),
            subtitle = flavor
        )

        SummaryItem(
            modifier = modifier,
            title = stringResource(R.string.pickup_date),
            subtitle = pickupDate
        )

        Text(
            text = stringResource(R.string.total_price, price).uppercase(),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.side_margin)),
            color = LocalColors.current.colorText,
            textAlign = TextAlign.End,
            style = LocalTypography.current.textAppearanceSubtitle1
        )

        SummaryBottomButtons(
            modifier = modifier,
            onSendClick = onSendClick,
            onCancelClick = onCancelClick
        )
    }
}

@Composable
fun SummaryItem(
    modifier: Modifier,
    title: String,
    subtitle: String
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            modifier = modifier,
            text = title.uppercase(),
            style = LocalTypography.current.textAppearanceSubtitle1
        )

        Text(
            modifier = modifier
                .padding(top = dimensionResource(R.dimen.order_summary_margin)),
            text = subtitle,
            style = LocalTypography.current.textAppearanceBold18
        )

        HorizontalDivider(
            modifier = modifier
                .padding(
                    top = dimensionResource(R.dimen.side_margin),
                    bottom = dimensionResource(R.dimen.side_margin)
                ),
            thickness = 1.dp
        )
    }
}

@Composable
fun SummaryBottomButtons(
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.side_margin))
            .fillMaxWidth(),
    ) {

        OutlinedButton(
            onClick = onSendClick,
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = LocalColors.current.colorOnPrimary,
                contentColor = LocalColors.current.colorPrimary,
                disabledContainerColor = LocalColors.current.colorSecondary,
                disabledContentColor = LocalColors.current.colorOnSecondary
            ),
            border = BorderStroke(2.dp, LocalColors.current.colorBorder)
        ) {

            Text(
                text = stringResource(R.string.send).uppercase()
            )
        }

        Button(
            onClick = onCancelClick,
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = LocalColors.current.colorPrimary,
                contentColor = LocalColors.current.colorOnPrimary,
                disabledContainerColor = LocalColors.current.colorSecondary,
                disabledContentColor = LocalColors.current.colorOnSecondary
            )
        ) {

            Text(
                text = stringResource(R.string.cancel).uppercase()
            )
        }
    }
}
