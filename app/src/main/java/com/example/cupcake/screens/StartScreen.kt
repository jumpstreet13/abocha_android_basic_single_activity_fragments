package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R

@Composable
fun StartScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(R.drawable.cupcake),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier.size(300.dp)
        )

        Text(
            text = stringResource(R.string.order_cupcakes),
            fontSize = 34.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(com.google.android.material.R.color.material_on_background_emphasis_medium),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = { },
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
        ) {
            Text(
                text = stringResource(R.string.one_cupcake).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = { },
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
        ) {
            Text(
                text = stringResource(R.string.six_cupcakes).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = { },
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
        ) {
            Text(
                text = stringResource(R.string.twelve_cupcakes).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel")
@Composable
fun StartScreenPreview() {
    MaterialTheme {
        StartScreen()
    }
}