package com.example.supletanes.ui.navigation

// --- Importaciones para Animaciones y Navegación ---
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset // Importación necesaria para el tween
// Componentes principales del sistema de navegación de Jetpack Compose.
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// --- Importaciones de las Pantallas de la App ---
import com.example.supletanes.ui.screens.AuthScreen
import com.example.supletanes.ui.screens.MainScreen
import com.example.supletanes.ui.screens.WelcomeScreen
import com.example.supletanes.ui.screens.profile.ChangeNameScreen
import com.example.supletanes.ui.screens.profile.ChangePasswordScreen
import com.example.supletanes.ui.screens.profile.PrivacyScreen

/**
 * Composable principal que define el grafo de navegación de toda la aplicación.
 * Gestiona qué pantalla se muestra y cómo se realizan las transiciones entre ellas.
 * Utiliza un NavHost para registrar todas las rutas y sus correspondientes Composables.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // --- Definición de Animaciones Estándar ---
    // CORRECCIÓN: Se especifica `IntOffset` para que sea compatible con las animaciones de deslizamiento.
    val animationSpec = tween<IntOffset>(durationMillis = 300)

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // --- Pantalla de Bienvenida (Punto de Entrada) ---
        composable(
            route = Screen.Welcome.route,
            exitTransition = { fadeOut(animationSpec = tween(300)) }
        ) {
            WelcomeScreen(
                onContinueClicked = {
                    navController.navigate(Screen.Auth.route)
                }
            )
        }

        // --- Pantalla de Autenticación (Login/Registro) ---
        composable(
            route = Screen.Auth.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = animationSpec) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = animationSpec) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = animationSpec) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = animationSpec) }
        ) {
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

        // --- Pantalla Principal (Contenido de la App) ---
        composable(
            route = Screen.Main.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = animationSpec) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = animationSpec) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = animationSpec) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = animationSpec) }
        ) {
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

        // --- Pantallas Secundarias (Formularios de Perfil) ---
        // Se reutilizan las mismas animaciones para consistencia.
        composable(
            route = Screen.ChangeName.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = animationSpec) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = animationSpec) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = animationSpec) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = animationSpec) }
        ) {
            ChangeNameScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.ChangePassword.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = animationSpec) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = animationSpec) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = animationSpec) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = animationSpec) }
        ) {
            ChangePasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Privacy.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = animationSpec) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = animationSpec) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = animationSpec) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = animationSpec) }
        ) {
            PrivacyScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
