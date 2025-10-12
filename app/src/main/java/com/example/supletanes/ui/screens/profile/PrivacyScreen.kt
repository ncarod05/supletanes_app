package com.example.supletanes.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PrivacyScreen(
    // --- INICIO DE LA MODIFICACIÓN ---
    // 1. Recibe la acción para navegar hacia atrás
    onNavigateBack: () -> Unit,
    // --- FIN DE LA MODIFICACIÓN ---
    privacyViewModel: PrivacyViewModel = viewModel()
) {

    // --- INICIO DE LA MODIFICACIÓN ---
    // 2. Escucha los eventos del ViewModel
    LaunchedEffect(key1 = true) {
        privacyViewModel.uiEvent.collect { event ->
            when (event) {
                is PrivacyViewModel.UiEvent.NavigateBack -> {
                    onNavigateBack() // Ejecuta la navegación
                }
            }
        }
    }
    // --- FIN DE LA MODIFICACIÓN ---

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            "Privacidad",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Boletín informativo", style = MaterialTheme.typography.bodyLarge)
                Text(
                    "Recibir promociones y noticias por correo electrónico.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Switch(
                checked = privacyViewModel.receivePromotions,
                onCheckedChange = { privacyViewModel.onReceivePromotionsChange(it) }
            )
        }
    }
}
