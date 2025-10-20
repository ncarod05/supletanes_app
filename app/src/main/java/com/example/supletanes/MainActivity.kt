// Ruta: app/src/main/java/com/example/supletanes/MainActivity.kt
package com.example.supletanes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.supletanes.ui.navigation.AppNavigation
import com.example.supletanes.ui.theme.SupletanesTheme
import com.example.supletanes.util.NotificationHelper

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // Aquí puedes manejar si el permiso fue concedido o no, si es necesario.
        // Por ahora, solo lo solicitamos.
    }

    private fun askNotificationPermission() {
        // Solo es necesario en Android 13 (TIRAMISU) y superior.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                // Si el permiso no está concedido, lo solicitamos.
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Llamar a la solicitud de permiso y crear el canal
        askNotificationPermission()
        NotificationHelper.createNotificationChannel(this)

        enableEdgeToEdge()
        setContent {
            SupletanesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
