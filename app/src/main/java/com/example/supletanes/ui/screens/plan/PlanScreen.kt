package com.example.supletanes.ui.screens.plan

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mi Plan Diario",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Sección 1: Desayuno
        PlanSection(title = "Desayuno", onAddItemClicked = {})
        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Sección 2: Almuerzo
        PlanSection(title = "Almuerzo", onAddItemClicked = {})
        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Sección 3: Cena
        PlanSection(title = "Cena", onAddItemClicked = {})
    }
}

@Composable
fun PlanSection(title: String, onAddItemClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        // Botón "Añadir"
        OutlinedButton(
            onClick = onAddItemClicked,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Añadir", style = MaterialTheme.typography.labelLarge)
        }
    }

    // Aquí irían los ítems del plan una vez implementados.
    Text(
        text = "Aún no hay ítems añadidos.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
    )
}