package com.example.supletanes.ui.screens.products

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.supletanes.data.db.entities.Suplemento
import com.example.supletanes.ui.screens.products.components.AddSupplementDialog
import com.example.supletanes.ui.screens.products.viewmodel.SuplementoViewModel
import com.example.supletanes.ui.screens.products.viewmodel.SuplementoViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen() {
    val viewModel: SuplementoViewModel = viewModel(
        factory = SuplementoViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    val suplementos by viewModel.allSupplements.collectAsState(initial = emptyList())

    // Controla si el diálogo para añadir suplementos está visible.
    var showDialog by remember { mutableStateOf(false) }

    var suplementoAEditar by remember { mutableStateOf<Suplemento?>(null) }

    // Si `showDialog` es true, muestra el diálogo.
    if (showDialog) {
        AddSupplementDialog(
            onDismissRequest = { showDialog = false }, // Para cerrar el diálogo
            onConfirm = { newSupplement ->
                viewModel.insert(newSupplement) // Llama al ViewModel para guardar
                showDialog = false // Cierra el diálogo después de guardar
            }
        )
    }

    if (suplementoAEditar != null) {
        AddSupplementDialog(
            suplementoInicial = suplementoAEditar,
            onDismissRequest = { suplementoAEditar = null },
            onConfirm = { suplementoEditado ->
                viewModel.update(suplementoEditado)
                suplementoAEditar = null
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Suplementos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            // Al hacer clic, se muestra el diálogo.
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Suplemento")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (suplementos.isEmpty()) {
                item {
                    Text(
                        text = "No hay suplementos en el catálogo. ¡Añade uno con el botón '+'!",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(suplementos, key = { it.id }) { suplemento ->
                    SuplementoItem(
                        suplemento = suplemento,
                        onDelete = { viewModel.delete(suplemento.id) },
                        onEdit = { suplementoAEditar = suplemento }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun SuplementoItem(suplemento: Suplemento, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = suplemento.nombre, style = MaterialTheme.typography.titleLarge)
                Text(text = suplemento.descripcion, style = MaterialTheme.typography.bodyMedium)
                Text(text = "$${suplemento.precio}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(text = "Stock: ${suplemento.stock}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Editar Suplemento", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar Suplemento", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}