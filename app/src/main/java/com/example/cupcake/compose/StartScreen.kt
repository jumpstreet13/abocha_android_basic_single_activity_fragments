package com.example.cupcake.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupCakeTheme
import com.example.cupcake.ui.theme.Paddings

@Composable
fun StartScreen(
    navController: NavHostController,
    sharedViewModel: OrderViewModel,
) {
    val context = LocalContext.current
    fun orderCupcake(numberCupcakes: Int) {
        sharedViewModel.setQuantity(numberCupcakes)
        // If no flavor is set in the view model yet, select vanilla as default flavor
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(context.getString(R.string.vanilla))
        }

        navController.navigate(Navigation.FLAVOR_SCREEN.route)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(stringResource(id = R.string.app_name)) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cupcake),
                    contentDescription = "",
                    modifier = Modifier
                        .absolutePadding(top = Paddings.xsmall)
                        .size(300.dp),
                    contentScale = ContentScale.Fit
                )

                Column(
                    modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.order_cupcakes),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .absolutePadding(bottom = Paddings.medium)
                    )

                    Button(
                        onClick = { orderCupcake(1) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(top = Paddings.xsmall),
                        shape = RoundedCornerShape(topStart = 0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.one_cupcake),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Button(
                        onClick = { orderCupcake(6) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(top = Paddings.xsmall),
                        shape = RoundedCornerShape(topStart = 0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.six_cupcakes),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Button(
                        onClick = { orderCupcake(12) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(top = Paddings.xsmall),
                        shape = RoundedCornerShape(topStart = 0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.twelve_cupcakes),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )


}

@Preview(
    showBackground = true
)
@Composable
fun PreviewStartScreen() {
    CupCakeTheme {
        StartScreen(
            navController = rememberNavController(),
            sharedViewModel = viewModel()
        )
    }
}