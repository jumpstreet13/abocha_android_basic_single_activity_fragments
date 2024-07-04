package com.example.cupcake

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CupcakeApp(::sendOrder)
        }
    }

    private fun sendOrder(orderSummary: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            putExtra(Intent.EXTRA_TEXT, orderSummary)
        }

        if (packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}
