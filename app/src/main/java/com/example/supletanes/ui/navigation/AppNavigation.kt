package com.example.supletanes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supletanes.ui.screens.AuthScreen
import com.example.supletanes.ui.screens.MainScreen
import com.example.supletanes.ui.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route // Empezamos en la pantalla de bienvenida
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                onContinueClicked = {
                    navController.navigate(Screen.Auth.route)
                }
            )
        }

        composable(route = Screen.Auth.route) {
            AuthScreen(
                onLoginSuccess = {
                    // Al iniciar sesión, vamos a la pantalla principal y limpiamos la pila de navegación
                    // para que el usuario no pueda volver atrás a la pantalla de Auth o Welcome.
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onContinueAsGuest = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }
    }
}
