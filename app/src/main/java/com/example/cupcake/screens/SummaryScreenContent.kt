package com.example.cupcake.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    cancel: () -> Unit,
) {

    SummaryScreenContent(
        modifier = modifier,
    )
}

@Composable
fun SummaryScreenContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var quantity by remember { mutableIntStateOf(6) }
    var flavor by remember { mutableStateOf("Chocolate") }
    var pickupDate by remember { mutableStateOf("Sunday") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.quantity).uppercase(),
            fontSize = 16.sp
        )
        Text(
            text = "$quantity cupcake",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.flavor).uppercase(),
            fontSize = 16.sp
        )
        Text(
            text = flavor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.pickup_date).uppercase(),
            fontSize = 16.sp
        )
        Text(
            text = pickupDate,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "TOTAL $10.00",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.send).uppercase())
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.cancel).uppercase())
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(device = "id:pixel", showSystemUi = true, showBackground = true)
@Composable
fun SummaryScreenPreview() {
    SummaryScreenContent()
}