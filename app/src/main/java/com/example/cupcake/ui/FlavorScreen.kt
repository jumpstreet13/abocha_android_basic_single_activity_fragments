package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.LocalNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.atom.CupCakeAppBar
import com.example.cupcake.ui.atom.RadioButtonGroup
import com.example.cupcake.ui.theme.CupCakeTheme


@Preview(showBackground = true)
@Composable
fun PreviewFlavorScreen() {
    CupCakeTheme {
        FlavorScreen(
            viewModel = viewModel()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPickUprScreen() {
    CupCakeTheme {
        FlavorScreen(
            viewModel = viewModel(),
            isFlavorScreen = false
        )
    }
}

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel,
    isFlavorScreen: Boolean = true
) {
    val navController = LocalNavController.current
    val priceText = viewModel.price.observeAsState().value ?: ""
    val topBarTitle =
        if (isFlavorScreen) stringResource(id = R.string.flavor)
        else stringResource(id = R.string.pickup_date)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(topBarTitle) { navController.popBackStack() } }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            DisplayRadioButtonGroup(isFlavorScreen, viewModel)
            HorizontalDivider(thickness = 2.dp)
            Text(
                text = stringResource(id = R.string.subtotal_price, priceText),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ActionButtons(navController, isFlavorScreen)
        }
    }
}

@Composable
fun DisplayRadioButtonGroup(isFlavorScreen: Boolean, viewModel: OrderViewModel) {
    val radioOptions = if (isFlavorScreen) {
        listOf(
            stringResource(id = R.string.vanilla),
            stringResource(id = R.string.chocolate),
            stringResource(id = R.string.red_velvet),
            stringResource(id = R.string.salted_caramel),
            stringResource(id = R.string.coffee)
        )
    } else {
        viewModel.dateOptions
    }

    val selectedOption = if (isFlavorScreen) {
        viewModel.flavor.observeAsState().value ?: ""
    } else {
        viewModel.date.observeAsState().value ?: ""
    }

    RadioButtonGroup(
        radioOptions = radioOptions,
        selectedOption = selectedOption,
        onOptionSelected = { text ->
            if (isFlavorScreen) {
                viewModel.setFlavor(text)
            } else {
                viewModel.setDate(text)
            }
        }
    )
}

@Composable
fun ActionButtons(navController: NavHostController, isFlavorScreen: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { navController.navigate(Navigation.START_SCREEN.route) },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.cancel).uppercase())
        }

        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = {
                navController.navigate(
                    if (isFlavorScreen) Navigation.PICKUP_SCREEN.route else Navigation.SUMMARY_SCREEN.route
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.next).uppercase())
        }
    }
}