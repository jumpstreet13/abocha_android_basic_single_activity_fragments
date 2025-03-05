package com.example.cupcake.presentation.start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.theme.ComposeSampleAppTheme
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.theme.LocalTypography

@Composable
fun StartScreen(
    goCustom: () -> Unit,
    onClickOrderButton: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberScrollState()

    AnimatedVisibility(
        visible = true
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.side_margin)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StartImage()
            StartTitle()
            OrderButton(
                text = stringResource(R.string.one_cupcake)
            ) {
                onClickOrderButton.invoke(1)
            }
            OrderButton(
                text = stringResource(R.string.six_cupcakes)
            ) {
                onClickOrderButton.invoke(6)
            }
            OrderButton(
                text = stringResource(R.string.twelve_cupcakes)
            ) {
                onClickOrderButton.invoke(12)
            }
            OrderButton(
                text = "go custom"
            ) {
                goCustom.invoke()
            }
        }
    }
}

@Composable
private fun StartImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.margin_between_elements))
            .size(dimensionResource(R.dimen.image_size)),
        painter = painterResource(R.drawable.cupcake),
        contentDescription = "",
        contentScale = ContentScale.Inside,
    )
}

@Composable
private fun StartTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(bottom = dimensionResource(R.dimen.side_margin)),
        text = stringResource(R.string.order_cupcakes),
        textAlign = TextAlign.Center,
        style = LocalTypography.current.textAppearanceHeadline34,
        color = LocalColor.current.textColor
    )
}

@Composable
private fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        colors = ButtonColors(
            containerColor = LocalColor.current.colorPrimary,
            contentColor = LocalColor.current.colorOnPrimary,
            disabledContainerColor = LocalColor.current.colorSecondary,
            disabledContentColor = LocalColor.current.colorOnSecondary
        ),
        shape = RoundedCornerShape(2.dp),
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.margin_between_elements))
            .defaultMinSize(dimensionResource(R.dimen.order_cupcake_button_width)),
        onClick = onClick
    ) {
        Text(text = text)
    }

}

@Preview
@Composable
fun StartScreenPreview() {
    ComposeSampleAppTheme/*(darkTheme = true)*/ {
        StartScreen(
            {}, {}, Modifier.background(LocalColor.current.colorOnPrimary)
        )
    }
}