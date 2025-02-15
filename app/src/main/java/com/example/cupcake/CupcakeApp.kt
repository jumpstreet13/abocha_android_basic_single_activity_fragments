package com.example.cupcake

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screens.*

@Composable
fun CupcakeApp(viewModel: OrderViewModel) {
    Surface {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Start
        ) {
            composable<Start>(
                enterTransition = { customSlideInHorizontally(isPop = false) },
                exitTransition = { customSlideOutHorizontally(isPop = false) },
                popEnterTransition = { customSlideInHorizontally (isPop = true) },
                popExitTransition = { customSlideOutHorizontally(isPop = true) }
            ) {
                StartScreen(navController, viewModel)
            }

            composable<Flavor>(
                enterTransition = { customSlideInHorizontally(isPop = false) },
                exitTransition = { customSlideOutHorizontally(isPop = false) },
                popEnterTransition = { customSlideInHorizontally (isPop = true) },
                popExitTransition = { customSlideOutHorizontally(isPop = true) }
            ) {
                FlavorScreen(navController, viewModel)
            }

            composable<Pickup>(
                enterTransition = { customSlideInHorizontally(isPop = false) },
                exitTransition = { customSlideOutHorizontally(isPop = false) },
                popEnterTransition = { customSlideInHorizontally (isPop = true) },
                popExitTransition = { customSlideOutHorizontally(isPop = true) }
            ) {
                PickupScreen(navController, viewModel)
            }

            composable<Summary>(
                enterTransition = { customSlideInHorizontally(isPop = false) },
                exitTransition = { customSlideOutHorizontally(isPop = false) },
                popEnterTransition = { customSlideInHorizontally (isPop = true) },
                popExitTransition = { customSlideOutHorizontally(isPop = true) }
            ) {
                SummaryScreen(navController, viewModel)
            }
        }
    }
}

private fun customSlideInHorizontally(isPop: Boolean): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { if (isPop) -it else it },
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )
}

private fun customSlideOutHorizontally(isPop: Boolean): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { if (isPop) it else -it },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutLinearInEasing
        )
    )
}