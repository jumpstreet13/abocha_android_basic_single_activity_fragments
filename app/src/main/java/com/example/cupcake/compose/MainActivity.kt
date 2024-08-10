package com.example.cupcake.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cupcake.compose.ui.theme.CupcakeTheme
import com.example.cupcake.model.OrderViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CupcakeTheme {

            }
        }
    }
}