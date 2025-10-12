package com.example.supletanes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.supletanes.ui.navigation.BottomNavItem
import com.example.supletanes.ui.screens.cart.CartScreen
import com.example.supletanes.ui.screens.product.ProductListScreen
import com.example.supletanes.ui.screens.profile.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    // Controlador de navegación para el contenido principal (Productos, Perfil, Carrito)
    val mainNavController = rememberNavController()

    Scaffold(
        // Barra de navegación inferior
        bottomBar = {
            NavigationBar(
                // Color de fondo de la barra de navegación
                containerColor = MaterialTheme.colorScheme.surface // Usará BlancoPuro
            ) {
                // Obtenemos el estado actual de la pila de navegación para saber qué pantalla se muestra
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Lista de nuestros items de navegación
                val items = listOf(
                    BottomNavItem.Products,
                    BottomNavItem.Profile,
                    BottomNavItem.Cart
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route, // El item está seleccionado si su ruta coincide con la actual
                        label = { Text(item.title) },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        onClick = {
                            // Navegamos a la ruta del item clickeado
                            mainNavController.navigate(item.route) {
                                // Evita acumular un gran stack de pantallas al volver a seleccionar un item
                                popUpTo(mainNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Evita crear múltiples copias de la misma pantalla
                                launchSingleTop = true
                                // Restaura el estado al volver a una pantalla previamente seleccionada
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            // Color del indicador (la "píldora") cuando está seleccionado
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            // Color del icono cuando está seleccionado
                            selectedIconColor = MaterialTheme.colorScheme.primary, // Azul Acero
                            // Color del texto cuando está seleccionado
                            selectedTextColor = MaterialTheme.colorScheme.primary, // Azul Acero
                            // Color del icono cuando NO está seleccionado
                            unselectedIconColor = MaterialTheme.colorScheme.outline, // Gris Plata
                            // Color del texto cuando NO está seleccionado
                            unselectedTextColor = MaterialTheme.colorScheme.outline  // Gris Plata
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost interno para las pantallas principales
        NavHost(
            navController = mainNavController,
            startDestination = BottomNavItem.Products.route, // La pantalla de inicio será Productos
            modifier = Modifier.padding(innerPadding) // Aplica el padding del Scaffold
        ) {
            composable(BottomNavItem.Products.route) { ProductListScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(BottomNavItem.Cart.route) { CartScreen() }
        }
    }
}
