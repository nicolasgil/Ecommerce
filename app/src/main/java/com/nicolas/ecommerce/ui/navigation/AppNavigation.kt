package com.nicolas.ecommerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicolas.ecommerce.ui.screens.ScreenDetails
import com.nicolas.ecommerce.ui.screens.ScreenLobby
import com.nicolas.ecommerce.ui.screens.ScreenSplash
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            ScreenSplash(navController)
        }
        composable(AppScreen.LobbyScreen.route) {
            val viewModel = hiltViewModel<LobbyViewModel>()
            ScreenLobby(viewModel, navController)
        }
        composable(
            AppScreen.DetailScreen.route + "/{productId}",
            arguments = listOf(navArgument(name = "productId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val viewModel = hiltViewModel<LobbyViewModel>()
            val product = productId?.let { viewModel.getProductById(it) }

            ScreenDetails(navController, product)

        }
    }
}
