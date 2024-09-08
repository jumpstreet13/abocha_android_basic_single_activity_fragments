/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arkivanov.decompose.defaultComponentContext
import com.example.cupcake.navigation.RootComponent
import com.example.cupcake.screen.RootContent
import com.example.cupcake.ui.MyApplicationTheme

/**
 * Activity for cupcake order flow.
 */
class MainActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val statusBarLightColor = ContextCompat.getColor(this, R.color.pink_950)
        val statusBarDarkColor = ContextCompat.getColor(this, R.color.pink_950)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                statusBarLightColor,
                statusBarDarkColor,
            ),
        )

        val rootComponent = RootComponent(defaultComponentContext())

        setContent {
            MyApplicationTheme {
                RootContent(rootComponent)
            }
        }
    }
}
