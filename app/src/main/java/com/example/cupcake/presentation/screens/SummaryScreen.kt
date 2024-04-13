package com.example.cupcake.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.cupcake.theme.Title

@Composable
fun SummaryScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {
    val price = viewModel.price.collectAsState()
    val quantity = viewModel.quantity.collectAsState()
    val flavor = viewModel.flavor.collectAsState()
    val date = viewModel.date.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {

        Title(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin)),
            title = stringResource(R.string.quantity)
        )
        InfoText(
            title = when (quantity.value) {
                1 -> stringResource(id = R.string.one_cupcake)
                6 -> stringResource(id = R.string.six_cupcakes)
                12 -> stringResource(id = R.string.twelve_cupcakes)
                else -> {
                    ""
                }
            }
        )

        Title(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin)),
            title = stringResource(R.string.flavor)
        )

        InfoText(title = flavor.value)

        Title(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin)),
            title = stringResource(R.string.pickup_date)
        )

        InfoText(title = date.value)

        HorizontalDivider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            thickness = 1.dp,
            color = Grey80
        )

        Text(
            modifier =
            Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin)),
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
            }) {
            InfoText(
                title = stringResource(R.string.send)
            )
        }

    }
}
