package com.example.cupcake

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.CupcakeApp
import com.example.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val orderViewModel by viewModels<OrderViewModel>()
            CupcakeTheme {
                CupcakeApp(
                    navController = navController,
                    viewModel = orderViewModel,
                    shareAction = ::share
                )
            }
        }
    }

    private fun share(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, text)

        if (packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}