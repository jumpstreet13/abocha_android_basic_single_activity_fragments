package com.example.cupcake.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.components.ListStep
import com.example.cupcake.model.NavigationRouts
import com.example.cupcake.viewModel.OrderViewModel
import com.example.cupcake.screens.base.BaseScreen
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PickupScreen(
    navHostController: NavHostController,
    viewModel: OrderViewModel
) {
    val scope = rememberCoroutineScope()
    val price = remember {
        mutableStateOf("")
    }
    val radioOptions = viewModel.dateOptions
    val selectedOption = remember { mutableStateOf(viewModel.date.value) }

    scope.launch {
        viewModel.price.collect {
            price.value = it
        }
    }

    LaunchedEffect(key1 = selectedOption.value) {
        selectedOption.value?.let {
            viewModel.setDate(it)
        }
    }

    BaseScreen {
        ListStep(
            radioOptions,
            selectedOption,
            price,
            onCancelButtonClick = {
                viewModel.resetOrder()
                navHostController.navigate(NavigationRouts.START.rout)
            },
            onNextButtonClick = {
                navHostController.navigate(NavigationRouts.SUMMARY.rout)
            }
        )
    }
}


@Composable
@Preview
private fun PickupScreenPreview() {
    PickupScreen(rememberNavController(), OrderViewModel())
}