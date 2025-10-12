package com.example.supletanes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Imports de las pantallas principales
import com.example.supletanes.ui.screens.AuthScreen
import com.example.supletanes.ui.screens.MainScreen
import com.example.supletanes.ui.screens.WelcomeScreen
// Imports de las pantallas secundarias del perfil
import com.example.supletanes.ui.screens.profile.ChangeNameScreen
import com.example.supletanes.ui.screens.profile.ChangePasswordScreen
import com.example.supletanes.ui.screens.profile.PrivacyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // --- Pantallas de Welcome y Auth (sin cambios) ---
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

        // --- Llamada a MainScreen (esto ya está correcto) ---
        composable(route = Screen.Main.route) {
            MainScreen(
                onLogoutClicked = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                },
                onChangeNameClicked = { navController.navigate(Screen.ChangeName.route) },
                onChangePasswordClicked = { navController.navigate(Screen.ChangePassword.route) },
                onPrivacyClicked = { navController.navigate(Screen.Privacy.route) }
            )
        }

        // --- INICIO DE LA REPARACIÓN ---
        // Aquí es donde estaba el error.
        // Ahora pasamos la acción onNavigateBack a cada pantalla de formulario.
        composable(route = Screen.ChangeName.route) {
            ChangeNameScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.ChangePassword.route) {
            ChangePasswordScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        // --- FIN DE LA REPARACIÓN ---

        // PrivacyScreen no necesita esta acción por ahora, así que se queda igual.
        composable(route = Screen.Privacy.route) {
            PrivacyScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
