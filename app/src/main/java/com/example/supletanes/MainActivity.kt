package com.example.supletanes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.supletanes.ui.navigation.AppNavigation
import com.example.supletanes.ui.theme.SupletanesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita que la app se dibuje de borde a borde.
        // Esto hace que las barras del sistema (status y navigation) sean transparentes.
        enableEdgeToEdge()
        setContent {
            SupletanesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Aquí llamamos a nuestro sistema de navegación
                    AppNavigation()
                }
            }
        }
    }
}
