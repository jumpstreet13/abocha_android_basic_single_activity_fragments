import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

@Composable
fun StartScreen(
    onClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Toolbar(title = stringResource(id = R.string.app_name)) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val image: Painter = painterResource(id = R.drawable.cupcake)
            Image(
                painter = image,
                contentDescription = "Cupcake Image",
                modifier = Modifier
                    .size(R.dimen.image_size.dp)
                    .padding(top = 8.dp, bottom = 16.dp),
            )
            Text(
                text = stringResource(id = R.string.order_cupcakes),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            CupcakeQuantityButtons(onClick)
        }
    }
}

@Composable
fun CupcakeQuantityButtons(onClick: (Int) -> Unit) {
    val quantities = listOf(
        1 to stringResource(id = R.string.one_cupcake),
        6 to stringResource(id = R.string.six_cupcakes),
        12 to stringResource(id = R.string.twelve_cupcakes)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        quantities.forEach { (quantity, text) ->
            Button(
                onClick = {
                    onClick(quantity)
                },
                modifier = Modifier
                    .sizeIn(minWidth = 250.dp)
            ) {
                Text(text = text)
            }
        }
    }
}
