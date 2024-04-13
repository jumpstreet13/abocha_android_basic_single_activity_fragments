package com.example.cupcake.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.Grey80
import com.example.cupcake.theme.InfoText

@Composable
fun FlavorScreen(
    radioOptions: List<String>,
    navController: NavController,
    viewModel: OrderViewModel,
    isFlavorScreen: Boolean
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    val price = viewModel.price.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            if (isFlavorScreen)
                                viewModel.setFlavor(text)
                            else
                                viewModel.setDate(text)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        if (isFlavorScreen)
                            viewModel.setFlavor(text)
                        else
                            viewModel.setDate(text)
                    }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.merge(),
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.White
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            thickness = 1.dp,
            color = Grey80
        )

        Text(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin)),
            text = price.value.toString(),
            lineHeight = 35.sp,
            fontSize = 25.sp,
            color = Grey80,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            textAlign = TextAlign.Right,
            overflow = TextOverflow.Ellipsis
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.side_margin)),
            onClick = {
                if (isFlavorScreen)
                    navController.navigate(AllScreen.PickupScreen.route)
                else
                    navController.navigate(AllScreen.SummaryScreen.route)
            }) {
            InfoText(
                title = stringResource(R.string.next)
            )
        }

    }
}