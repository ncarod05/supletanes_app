package com.example.supletanes.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNameScreen(
    // --- INICIO DE LA REPARACIÓN ---
    // 1. Añade este parámetro para recibir la acción de navegación.
    onNavigateBack: () -> Unit,
    // --- FIN DE LA REPARACIÓN ---
    changeNameViewModel: ChangeNameViewModel = viewModel()
) {
    val name = changeNameViewModel.name
    val nameError = changeNameViewModel.nameError

    // 2. Escucha los eventos del ViewModel para ejecutar la navegación.
    LaunchedEffect(key1 = true) {
        changeNameViewModel.uiEvent.collect { event ->
            when (event) {
                is ChangeNameViewModel.UiEvent.NavigateBack -> {
                    onNavigateBack() // Llama a la acción recibida.
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cambiar Nombre",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { changeNameViewModel.onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nuevo Nombre") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            singleLine = true,
            isError = nameError != null,
            supportingText = {
                if (nameError != null) {
                    Text(text = nameError)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { changeNameViewModel.onSaveClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Cambios")
        }
    }
}
