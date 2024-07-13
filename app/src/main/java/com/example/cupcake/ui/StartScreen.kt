package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.LocalNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.atom.CupCakeAppBar
import com.example.cupcake.ui.theme.CupCakeTheme

@Preview(
    showBackground = true
)
@Composable
fun PreviewStartScreen() {
    CupCakeTheme {
        StartScreen(
            viewModel = viewModel()
        )
    }
}

fun orderCupcake(numberCupcakes: Int, viewModel: OrderViewModel, navController: NavHostController) {
    viewModel.setQuantity(numberCupcakes)
    navController.navigate(Navigation.FLAVOR_SCREEN.route)
}

@Composable
fun StartScreen(viewModel: OrderViewModel) {
    val navController = LocalNavController.current
    if (viewModel.hasNoFlavorSet()) {
        viewModel.setFlavor(stringResource(R.string.vanilla))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(stringResource(id = R.string.app_name)) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Image(
                painter = painterResource(id = R.drawable.cupcake),
                contentDescription = null,
                Modifier
                    .padding(top = 16.dp)
                    .size(300.dp),
                contentScale = ContentScale.Fit
            )
            OrderScreen(viewModel, navController)
        }
    }
}

@Composable
fun OrderScreen(viewModel: OrderViewModel, navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (text, button1, button2, button3) = createRefs()

        Text(
            text = stringResource(id = R.string.order_cupcakes),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                }
        )

        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(1, viewModel, navController) },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(text.bottom, margin = 24.dp)
                start.linkTo(text.start)
                end.linkTo(text.end)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.one_cupcake))
        }

        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(6, viewModel, navController) },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(button1.bottom, margin = 8.dp)
                start.linkTo(button1.start)
                end.linkTo(button1.end)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.six_cupcakes))
        }

        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { orderCupcake(12, viewModel, navController) },
            modifier = Modifier.constrainAs(button3) {
                top.linkTo(button2.bottom, margin = 8.dp)
                start.linkTo(button2.start)
                end.linkTo(button2.end)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.twelve_cupcakes))
        }
    }
}

