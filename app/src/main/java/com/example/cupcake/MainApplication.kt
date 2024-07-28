package com.example.cupcake

import android.app.Application
import com.example.cupcake.di.DaggerApplicationComponent

class MainApplication: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}