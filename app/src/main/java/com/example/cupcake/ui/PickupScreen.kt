package com.example.cupcake.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupcakeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupScreen(
    sharedViewModel: OrderViewModel,
    onNavigateToStartScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text(text = stringResource(id = R.string.choose_flavor)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
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
        PickupScreenContent(modifier.padding(paddingValues))
    }
}

@Composable
fun PickupScreenContent(modifier: Modifier = Modifier) {
    Text("Pickup date", fontSize = 24.sp, modifier = modifier)
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
            PickupScreenContent()
        }
    }
}