package com.example.cupcake.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.ui.widgets.RadioButtonWithRipple
import com.example.cupcake.ui.widgets.RectangularFilledButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlavorScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text(text = stringResource(id = R.string.choose_flavor)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    )
    { paddingValues ->
        FlavorScreenContent(modifier.padding(paddingValues))
    }
}

@Composable
private fun FlavorScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val flavors = listOf(
            stringResource(R.string.vanilla),
            stringResource(R.string.chocolate),
            stringResource(R.string.red_velvet),
            stringResource(R.string.salted_caramel),
            stringResource(R.string.coffee),
        )
        FlavorPickerRadioGroup(flavors, modifier = Modifier.padding(start = 16.dp))

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        val price = remember { mutableStateOf("0.0") }

        SubtotalPrice(
            price = price,
            modifier = Modifier
                .padding(top = 0.dp, end = 16.dp)
                .align(alignment = Alignment.End)
        )

        NavigationButtons()
    }
}

@Composable
private fun FlavorPickerRadioGroup(flavors: List<String>, modifier: Modifier = Modifier) {
    val (selectedOption: String, onOptionSelected: (String) -> Unit) =
        remember { mutableStateOf(flavors[0]) }
    Column {
        Column(Modifier.selectableGroup()) {
            flavors.forEach { text ->
                RadioButtonWithRipple(
                    text = text,
                    selected = (text == selectedOption),
                    modifier = modifier.padding(vertical = 16.dp),
                    onClick = { onOptionSelected(text) }
                )
            }
        }
    }
}

@Composable
fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        thickness = 1.dp
    )
}

@Composable
fun SubtotalPrice(price: MutableState<String>, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, price.value),
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
private fun NavigationButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

        RectangularFilledButton(
            buttonText = stringResource(id = R.string.next),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        )
    }
}


@Preview(
    name = "Dark Mode With System UI",
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun FlavorScreenContentDarkWithSystemUi() {
    CupcakeTheme(darkTheme = true) {
        Surface {
            FlavorScreenContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentLightPreview() {
    CupcakeTheme(darkTheme = false) {
        FlavorScreenContent()
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentDarkPreview() {
    CupcakeTheme(darkTheme = true) {
        FlavorScreenContent()
    }
}
