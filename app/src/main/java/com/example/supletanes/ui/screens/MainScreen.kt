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
import com.example.supletanes.ui.screens.products.ProductsScreen
import com.example.supletanes.ui.screens.profile.ProfileScreen
// ✅ PASO 4: IMPORTAR EL MODELO UserProfile
import com.example.supletanes.ui.screens.profile.UserProfile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    isGuest: Boolean,
    // ✅ PASO 4: RECIBIR EL userProfile (puede ser nulo)
    userProfile: UserProfile?,
    onLogoutClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onChangeNameClicked: () -> Unit,
    onChangePasswordClicked: () -> Unit,
    onPrivacyClicked: () -> Unit
) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            // ... (Tu NavigationBar no cambia)
            NavigationBar {
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    BottomNavItem.Plan, BottomNavItem.Products,
                    BottomNavItem.Profile, BottomNavItem.Cart
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        label = { Text(item.title) },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        onClick = {
                            mainNavController.navigate(item.route) {
                                popUpTo(mainNavController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = BottomNavItem.Plan.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Plan.route) { PlanScreen() }
            composable(BottomNavItem.Products.route) { ProductsScreen() }
            composable(BottomNavItem.Profile.route) {
                // ✅ PASO 4: PASAR EL userProfile REAL A ProfileScreen
                ProfileScreen(
                    isGuest = isGuest,
                    user = userProfile, // <--- Aquí se pasan los datos
                    onLoginClicked = onLoginClicked,
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
