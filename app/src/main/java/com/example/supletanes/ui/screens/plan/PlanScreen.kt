package com.example.supletanes.ui.screens.plan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supletanes.ui.screens.plan.components.CalorieTracker
import com.example.supletanes.ui.screens.plan.components.PlanSection
import com.example.supletanes.ui.screens.plan.components.ProgressCheckInSection
import com.example.supletanes.ui.screens.plan.components.WeekTimeline

@Composable
fun PlanScreen() {
    // Definir la meta diaria de calorías (debería venir del ViewModel/Estado real)
    val dailyGoalCalories = 2500

    // LazyColumn para asegurar que todo el contenido sea deslizable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        // Relleno extra al final para que el último elemento no toque el borde inferior
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // Título de la pantalla
        item {
            Text(
                text = "Mi Plan Diario",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Línea de tiempo de la semana
        item {
            WeekTimeline()
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Rastreador de Calorías y Macronutrientes
        item {
            CalorieTracker(
                consumedCalories = 1200,
                goalCalories = dailyGoalCalories,
                protein = 80,
                goalProtein = 150,
                carbs = 150,
                goalCarbs = 300,
                fats = 50,
                goalFats = 100
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        // --- SECCIONES DE COMIDAS ACTUALIZADAS ---

        // Desayuno
        item {
            PlanSection(
                title = "Desayuno",
                sectionCalories = 450,
                goalCalories = dailyGoalCalories,
                onAddItemClicked = {}
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))
        }

        // Almuerzo
        item {
            PlanSection(
                title = "Almuerzo",
                sectionCalories = 600,
                goalCalories = dailyGoalCalories,
                onAddItemClicked = {}
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))
        }

        // Cena
        item {
            PlanSection(
                title = "Cena",
                sectionCalories = 150,
                goalCalories = dailyGoalCalories,
                onAddItemClicked = {}
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))
        }

        // Snacks
        item {
            PlanSection(
                title = "Snacks",
                sectionCalories = 0,
                goalCalories = dailyGoalCalories,
                onAddItemClicked = {}
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        // --- SECCION DE CHECK-IN DE PROGRESO ---
        item {
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            ProgressCheckInSection(
                userGoal = "Perder 5 kg",
                initialWeight = 85.0,
                currentWeight = 82.5,
                onWeightCheckInClicked = {
                    // Lógica para abrir un diálogo o navegar para actualizar el peso
                    println("Iniciando Check-in de Peso...")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanScreenPreview() {
    PlanScreen()
}