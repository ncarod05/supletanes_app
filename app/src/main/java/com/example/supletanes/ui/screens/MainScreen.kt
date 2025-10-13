package com.example.supletanes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
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
import com.example.supletanes.ui.screens.plan.PlanScreen
import com.example.supletanes.ui.screens.profile.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    // --- INICIO DE LA REPARACIÓN ---
    // La firma de la función AHORA acepta todos los parámetros que le pasas.
    onLogoutClicked: () -> Unit,
    onChangeNameClicked: () -> Unit,
    onChangePasswordClicked: () -> Unit,
    onPrivacyClicked: () -> Unit
    // --- FIN DE LA REPARACIÓN ---
) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    BottomNavItem.Products,
                    BottomNavItem.Profile,
                    BottomNavItem.Cart
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        label = { Text(item.title) },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        onClick = {
                            mainNavController.navigate(item.route) {
                                popUpTo(mainNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.outline,
                            unselectedTextColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = BottomNavItem.Products.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Products.route) { PlanScreen() }

            composable(BottomNavItem.Profile.route) {
                // Y aquí, pasa todos los parámetros hacia ProfileScreen
                ProfileScreen(
                    onLogoutClicked = onLogoutClicked,
                    onChangeNameClicked = onChangeNameClicked,
                    onChangePasswordClicked = onChangePasswordClicked,
                    onPrivacyClicked = onPrivacyClicked
                )
            }

            composable(BottomNavItem.Cart.route) { CartScreen() }
        }
    }
}
