package com.example.cupcake.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.Gray1200
import com.example.cupcake.theme.Grey80
import com.example.cupcake.theme.InfoText
import com.example.cupcake.theme.MyApplicationTheme

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {

        Image(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .width(dimensionResource(id = R.dimen.image_size))
                .height(dimensionResource(id = R.dimen.image_size)),
            painter = painterResource(R.drawable.cupcake), contentDescription = "cupcake"
        )

        Text(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin)),
            text = stringResource(R.string.order_cupcakes),
            lineHeight = 35.sp,
            fontSize = 35.sp,
            color = Grey80,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Button(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
            onClick = {
                viewModel.setQuantity(1)
                navController.navigate(AllScreen.FlavorScreen.route)
            }) {
            InfoText(title =stringResource(R.string.one_cupcake)
            )
        }

        Button(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
            onClick = {
                viewModel.setQuantity(6)
                navController.navigate(AllScreen.FlavorScreen.route)
            }) {
            InfoText(title =stringResource(R.string.six_cupcakes))
        }

        Button(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
            onClick = {
                viewModel.setQuantity(12)
                navController.navigate(AllScreen.FlavorScreen.route)
            }) {
            InfoText(title = stringResource(R.string.twelve_cupcakes))
        }
    }
}


@Preview
@Composable
fun previewMainScreen() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Gray1200
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
            ) {

                Image(
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .width(dimensionResource(id = R.dimen.image_size))
                        .height(dimensionResource(id = R.dimen.image_size)),
                    painter = painterResource(R.drawable.cupcake), contentDescription = "cupcake"
                )

                    Text(
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin)),
                        text = stringResource(R.string.order_cupcakes),
                        lineHeight = 35.sp,
                        fontSize = 35.sp,
                        color = Grey80,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Button(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                            .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
                        onClick = { },
                    )
                    {
                        InfoText(title = stringResource(R.string.one_cupcake))
                    }

                    Button(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                            .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
                        onClick = { },
                    )
                    {
                        InfoText(title = stringResource(R.string.six_cupcakes))
                    }

                    Button(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.order_cupcake_button_width))
                            .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
                        onClick = { },
                    )
                    {
                        InfoText(title = stringResource(R.string.twelve_cupcakes))
                    }
                }
        }
    }
}