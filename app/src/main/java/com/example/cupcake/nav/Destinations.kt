package com.example.cupcake.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R

interface Destination {
    val route: String
    val navIcon: ImageVector?
    val titleResId: Int
}

object Start: Destination {
    override val navIcon = null
    override val route = "start"
    override val titleResId = R.string.app_name
}

object ChooseFlavor: Destination {
    override val navIcon = Icons.AutoMirrored.Filled.ArrowBack
    override val route = "choose_flavor"
    override val titleResId = R.string.choose_flavor
}

object PickupDate: Destination {
    override val navIcon = Icons.AutoMirrored.Filled.ArrowBack
    override val route = "pickup_date"
    override val titleResId = R.string.choose_pickup_date
}

object Summary: Destination {
    override val navIcon = Icons.AutoMirrored.Filled.ArrowBack
    override val route = "summary"
    override val titleResId = R.string.order_summary
}


val Destinations = listOf(Start, ChooseFlavor, PickupDate, Summary)