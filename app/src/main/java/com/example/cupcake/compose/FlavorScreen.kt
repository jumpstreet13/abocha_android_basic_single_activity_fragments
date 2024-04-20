package com.example.cupcake.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupCakeTheme
import com.example.cupcake.ui.theme.DividerHorizontal
import com.example.cupcake.ui.theme.Paddings

@Composable
fun FlavorScreen(
    navController: NavHostController,
    viewModel: OrderViewModel,
    isFlavorScreen: Boolean = true
) {
    val priceText = remember {
        mutableStateOf("${viewModel.price.value}")
    }

    val appBarText = stringResource(
        id = if (isFlavorScreen) {
            R.string.choose_flavor
        } else {
            R.string.choose_pickup_date
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(appBarText) { navController.popBackStack() } },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(Paddings.medium)
            ) {

                MultipleOptions(viewModel, isFlavorScreen, priceText)

                HorizontalDivider(
                    modifier = Modifier.padding(top = Paddings.medium),
                    thickness = 1.dp,
                    color = DividerHorizontal
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = Paddings.medium),
                    text = stringResource(R.string.total_price, priceText.value),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Paddings.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .padding(end = Paddings.xsmall)
                            .weight(0.5f),
                        onClick = {
                            viewModel.resetOrder()
                            navController.navigate(Navigation.START_SCREEN.route)
                        },
                        colors = buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ),
                        border = BorderStroke(1.dp, DividerHorizontal),
                        shape = RoundedCornerShape(topStart = 4.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(start = Paddings.xsmall)
                            .weight(0.5f),
                        onClick = {
                            if (isFlavorScreen)
                                navController.navigate(Navigation.PICKUP_SCREEN.route)
                            else
                                navController.navigate(Navigation.SUMMARY_SCREEN.route)
                        },
                        shape = RoundedCornerShape(topStart = 4.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.next),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun MultipleOptions(
    viewModel: OrderViewModel,
    isFlavorScreen: Boolean,
    priceText: MutableState<String>
) {
    val options: List<String> = if (isFlavorScreen) {
        listOf(
            stringResource(id = R.string.vanilla),
            stringResource(id = R.string.chocolate),
            stringResource(id = R.string.red_velvet),
            stringResource(id = R.string.salted_caramel),
            stringResource(id = R.string.coffee)
        )
    } else {
        listOf(
            viewModel.dateOptions[0],
            viewModel.dateOptions[1],
            viewModel.dateOptions[2],
            viewModel.dateOptions[3]
        )
    }


    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            if (isFlavorScreen) {
                viewModel.flavor.value
            } else {
                viewModel.date.value
            }
        )
    }

    val isOptionSelected: (String) -> Boolean = { selectedOption == it }

    fun onOptionClick(text: String) {
        onOptionSelected(text)
        if (isFlavorScreen) {
            viewModel.setFlavor(text)
        } else {
            viewModel.setDate(text)
            priceText.value = viewModel.price.value.orEmpty()
        }
    }

    Column(
        modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max),
        horizontalAlignment = Alignment.Start
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = isOptionSelected.invoke(text),
                        onClick = { onOptionClick(text) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                RadioButton(
                    selected = isOptionSelected.invoke(text),
                    onClick = { onOptionClick(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFlavorScreen() {
    CupCakeTheme {
        FlavorScreen(
            navController = rememberNavController(),
            viewModel = viewModel(),
            isFlavorScreen = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPickUprScreen() {
    CupCakeTheme {
        FlavorScreen(
            navController = rememberNavController(),
            viewModel = viewModel(),
            isFlavorScreen = false
        )
    }
}