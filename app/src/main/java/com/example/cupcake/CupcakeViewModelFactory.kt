package com.example.cupcake

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CupcakeViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CupcakeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CupcakeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}