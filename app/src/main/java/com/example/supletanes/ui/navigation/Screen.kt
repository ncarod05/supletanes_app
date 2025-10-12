// En: ui/navigation/Screen.kt
package com.example.supletanes.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Auth : Screen("auth_screen")
    object Main : Screen("main_screen")
    object ChangeName : Screen("change_name_screen")
    object ChangePassword : Screen("change_password_screen")
    object Privacy : Screen("privacy_screen")
}
