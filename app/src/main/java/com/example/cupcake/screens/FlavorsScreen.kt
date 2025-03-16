package com.example.cupcake.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cupcake.R

@Preview
@Composable
fun FlavorsScreen(
    modifier: Modifier = Modifier,
    selectedFlavor: String? = "",
    onFlavorSelected: (String) -> Unit = {},
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    price: LiveData<Double> = MutableLiveData(0.0)
) = OptionSelectionScreen(
    modifier = modifier,
    options = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    ),
    defaultOption = selectedFlavor ?: stringResource(R.string.vanilla),
    onOptionSelected = onFlavorSelected,
    onCancelClick = onCancelClick,
    onNextClick = onNextClick,
    price = price
)