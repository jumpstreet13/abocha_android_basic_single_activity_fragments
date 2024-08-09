package com.example.cupcake.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R.string
import com.example.cupcake.components.ListStep
import com.example.cupcake.model.NavigationRouts
import com.example.cupcake.viewModel.OrderViewModel
import com.example.cupcake.screens.base.BaseScreen
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FlavorScreen(
    navHostController: NavHostController,
    viewModel: OrderViewModel,
) {
    val scope = rememberCoroutineScope()
    val price = remember {
        mutableStateOf("")
    }
    val radioOptions = listOf(
        stringResource(id = string.vanilla),
        stringResource(id = string.chocolate),
        stringResource(id = string.red_velvet),
        stringResource(id = string.salted_caramel),
        stringResource(id = string.coffee)
    )
    val selectedOption = remember { mutableStateOf(viewModel.flavor.value) }

    scope.launch {
        viewModel.price.collect {
            price.value = it
        }
    }

    LaunchedEffect(key1 = selectedOption.value) {
        selectedOption.value?.let {
            viewModel.setFlavor(it)
        }
    }

    BaseScreen {
        ListStep(
            radioOptions,
            selectedOption,
            price,
            onCancelButtonClick = {
                viewModel.resetOrder()
                navHostController.navigateUp()
            },
            onNextButtonClick = {
                navHostController.navigate(NavigationRouts.PICKUP.rout)
            }
        )
    }
}

@Composable
@Preview
private fun PickupScreenPreview() {
    FlavorScreen(rememberNavController(), OrderViewModel())
}