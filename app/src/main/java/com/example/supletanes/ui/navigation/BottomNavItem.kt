package com.example.supletanes.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Products : BottomNavItem(
        title = "Productos",
        icon = Icons.Default.Home,
        route = "products"
    )
    object Profile : BottomNavItem(
        title = "Perfil",
        icon = Icons.Default.Person,
        route = "profile"
    )
    object Cart : BottomNavItem(
        title = "Carrito",
        icon = Icons.Default.ShoppingCart,
        route = "cart"
    )
}
