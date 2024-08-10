package com.example.cupcake.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cupcake.R
import com.example.cupcake.compose.navigation.CupcakeNavHost
import com.example.cupcake.compose.ui.theme.CupcakeTheme
import com.example.cupcake.model.OrderViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CupcakeTheme {
                CupcakeNavHost(
                    viewModel,
                    { orderCupcake(it) },
                    { sendOrder() }
                )
            }
        }
    }

    private fun orderCupcake(
        quantity: Int
    ) {
        viewModel.setQuantity(quantity)

        if (viewModel.hasNoFlavorSet()) {
            viewModel.setFlavor(getString(R.string.vanilla))
        }
    }

    private fun sendOrder() {
        val numberOfCupcakes = viewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            viewModel.flavor.value.toString(),
            viewModel.date.value.toString(),
            viewModel.price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        if (packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}