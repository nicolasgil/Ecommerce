package com.nicolas.ecommerce.ui.navigation

sealed class AppScreen(val route: String) {
    data object SplashScreen : AppScreen("splash_screen")
    data object LobbyScreen : AppScreen("lobby_screen")
    data object DetailScreen : AppScreen("detail_screen")
}