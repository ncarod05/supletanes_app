package com.example.supletanes.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Auth : Screen("auth_screen")
    object Main : Screen("main_screen")
}