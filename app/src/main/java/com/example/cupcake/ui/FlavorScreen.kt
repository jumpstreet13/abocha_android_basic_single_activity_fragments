package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.ui.view.Chooser
import com.example.cupcake.ui.view.Toolbar
import com.example.cupcake.ui.view.WizardButtons

@Composable
fun ChooseScreen(
    title: String,
    elements: List<String>,
    selectedElement: String,
    price: String,
    onElementClick: (element: String) -> Unit,
    onBackButtonClick: () -> Unit,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(
                text = title,
                onBackButtonClick = onBackButtonClick
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Chooser(
                elements = elements,
                selectedElement = selectedElement,
                onElementSelect = onElementClick,
            )
            Divider(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.subtotal_price, price),
                textAlign = TextAlign.End,
                fontSize = 18.sp,
            )
            WizardButtons(onCancelClick, onNextClick)
        }
    }
}

@Preview
@Composable
fun ChooseScreenPreview() {
    CupcakeTheme {
        ChooseScreen(
            title = "a-b-c-d",
            elements = listOf("a", "b", "c", "d"),
            price = "4",
            selectedElement = "b",
            onElementClick = {},
            onBackButtonClick = {},
            onCancelClick = {},
            onNextClick = {},
        )
    }
}