// En: ui/navigation/Screen.kt
package com.example.supletanes.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Auth : Screen("auth_screen")

    // ✅ PASO 1: MODIFICAR LA RUTA DE MAIN
    // La ruta ahora incluye un marcador de posición para un argumento llamado "isGuest".
    // También añadimos una función de ayuda para construir esta ruta de forma segura.
    object Main : Screen("main_screen/{isGuest}") {
        fun createRoute(isGuest: Boolean) = "main_screen/$isGuest"
    }

    object ChangeName : Screen("change_name_screen")
    object ChangePassword : Screen("change_password_screen")
    object Privacy : Screen("privacy_screen")
}
