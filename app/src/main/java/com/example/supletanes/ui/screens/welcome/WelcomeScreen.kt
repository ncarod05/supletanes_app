package com.example.supletanes.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.supletanes.R

@Composable
fun WelcomeScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.welogo),
            contentDescription = "Imagen de bienvenida", //Descripción para accesibilidad
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Bienvenido a Supletanes",
            style = MaterialTheme.typography.headlineLarge, //Título más grande
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = onContinueClicked, //La acción se pasa como parámetro
            modifier = Modifier.fillMaxWidth(0.8f), //Botón un poco más ancho
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary // Fondo Azul Acero
            )
        ) {
            Text("Continuar",
                color = MaterialTheme.colorScheme.onPrimary //BlancoPuro
            )
        }
    }
}

// Ver el diseño sin ejecutar la app
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onContinueClicked = {})
}
