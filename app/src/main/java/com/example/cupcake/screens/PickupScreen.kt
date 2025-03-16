package com.example.cupcake.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Preview
@Composable
fun PickupScreen(
    modifier: Modifier = Modifier,
    selectedDate: String = "Today",
    pickupDates: List<String> = listOf(
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    ),
    onDateSelected: (String) -> Unit = {},
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    price: LiveData<Double> = MutableLiveData(0.0)
) = OptionSelectionScreen(
    modifier = modifier,
    options = pickupDates,
    defaultOption = selectedDate,
    onOptionSelected = onDateSelected,
    onCancelClick = onCancelClick,
    onNextClick = onNextClick,
    price = price
)