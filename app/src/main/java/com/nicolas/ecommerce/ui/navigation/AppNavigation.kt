package com.nicolas.ecommerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolas.ecommerce.ui.screens.LobbyScreen
import com.nicolas.ecommerce.ui.screens.SplashScreen
import com.nicolas.ecommerce.ui.viewmodels.LobbyViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.LobbyScreen.route) {
            val viewModel = hiltViewModel<LobbyViewModel>()
            LobbyScreen(viewModel)
        }
    }
}