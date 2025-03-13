package com.example.cupcake.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cupcake.R
import com.example.cupcake.appTheme.LocalColors
import com.example.cupcake.appTheme.LocalTypography

@Preview
@Composable
fun OptionSelectionScreen(
    modifier: Modifier = Modifier,
    options: List<String> = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    ),
    defaultOption: String = "Vanilla",
    onOptionSelected: (String) -> Unit = {},
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    price: LiveData<Double> = MutableLiveData()
) {

    val scrollState = rememberScrollState()

    val currentPrice = price.observeAsState()

    val selectedFlavor = rememberSaveable { mutableStateOf(defaultOption) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.Start

    ) {

        options.forEach { flavor ->

            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = (selectedFlavor.value == flavor)
                        .also { if (it) onOptionSelected(flavor) },
                    onClick = {
                        selectedFlavor.value = flavor
                    }
                )

                Text(
                    text = flavor,
                    modifier = modifier,
                    color = LocalColors.current.colorText
                )

            }
        }

        HorizontalDivider(
            modifier = modifier
                .padding(top = dimensionResource(R.dimen.side_margin)),
            thickness = 1.dp
        )

        Text(
            text = stringResource(R.string.subtotal_price, currentPrice.value ?: ""),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.side_margin)),
            color = LocalColors.current.colorText,
            textAlign = TextAlign.End,
            style = LocalTypography.current.textAppearanceSubtitle1
        )

        OptionSelectionBottomButtons(
            modifier = modifier,
            onCancelClick = onCancelClick,
            onNextClick = onNextClick
        )
    }
}

@Composable
fun OptionSelectionBottomButtons(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.side_margin)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedButton(
            onClick = onCancelClick,
            modifier = modifier
                .weight(1F)
                .padding(end = dimensionResource(R.dimen.margin_between_elements)),
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
                text = stringResource(R.string.cancel).uppercase()
            )
        }

        Button(
            onClick = onNextClick,
            modifier = modifier
                .weight(1F)
                .padding(start = dimensionResource(R.dimen.margin_between_elements)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = LocalColors.current.colorPrimary,
                contentColor = LocalColors.current.colorOnPrimary,
                disabledContainerColor = LocalColors.current.colorSecondary,
                disabledContentColor = LocalColors.current.colorOnSecondary
            )
        ) {

            Text(
                text = stringResource(R.string.next).uppercase()
            )
        }

    }
}
