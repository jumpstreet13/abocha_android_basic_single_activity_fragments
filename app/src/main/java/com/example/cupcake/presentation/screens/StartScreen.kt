package com.example.cupcake.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.Destination
import com.example.cupcake.navigation.getScreenEnterTransition
import com.example.cupcake.navigation.getScreenExitTransition
import com.example.cupcake.presentation.components.AppScreenWrapper
import com.example.cupcake.uikit.AppButton

fun NavGraphBuilder.addStartDestination(
    navHostController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    composable<Destination.StartScreen>(
        enterTransition = getScreenEnterTransition(),
        exitTransition = getScreenExitTransition()
    ) {
        val context = LocalContext.current

        StartScreen { quantity ->
            sharedViewModel.setQuantity(quantity)
            if (sharedViewModel.hasNoFlavorSet()) {
                sharedViewModel.setFlavor(context.getString(R.string.vanilla))
            }
            navHostController.navigate(Destination.FlavorScreen)
        }
    }
}

@Composable
private fun StartScreen(onOrderButtonClick: (Int) -> Unit) {
    AppScreenWrapper(title = stringResource(id = R.string.app_name)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.cupcake),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(300.dp)
            )
            Text(
                text = stringResource(id = R.string.order_cupcakes),
                fontSize = 34.sp,
                color = Color(0x99000000),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AppButton(
                text = stringResource(id = R.string.one_cupcake),
                onClick = { onOrderButtonClick(1) },
                modifier = Modifier.padding(top = 8.dp)
            )
            AppButton(
                text = stringResource(id = R.string.six_cupcakes),
                onClick = { onOrderButtonClick(6) },
                modifier = Modifier.padding(top = 8.dp)
            )
            AppButton(
                text = stringResource(id = R.string.twelve_cupcakes),
                onClick = { onOrderButtonClick(12) },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStartScreen() {
    StartScreen {}
}