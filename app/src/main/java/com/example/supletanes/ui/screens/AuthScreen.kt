// Ruta: app/src/main/java/com/example/supletanes/ui/screens/AuthScreen.kt
package com.example.supletanes.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.supletanes.util.NotificationHelper

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onContinueAsGuest: () -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isContentVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isContentVisible = true
    }

    fun validateFields(): Boolean {
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "Todos los campos son obligatorios."
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "El formato del correo electrónico no es válido."
            return false
        }
        errorMessage = null
        return true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = isContentVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 350)) +
                        slideInVertically(
                            initialOffsetY = { it / 2 },
                            animationSpec = tween(durationMillis = 350)
                        )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Iniciar Sesión", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(32.dp))

                    // ... (Tus OutlinedTextFields y validaciones)

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nombre de Usuario") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage?.contains("campos") == true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage?.contains("correo") == true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        isError = errorMessage?.contains("campos") == true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }


                    Button(
                        onClick = {
                            if (validateFields()) {
                                isLoading = true
                                authViewModel.login(username = username, email = email)
                                NotificationHelper.showSimpleNotification(
                                    context = context,
                                    notificationId = 1,
                                    title = "¡Bienvenido de nuevo, $username!",
                                    text = "Has iniciado sesión correctamente."
                                )
                                onLoginSuccess()
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Text("Iniciar Sesión")
                        }
                    }

                    TextButton(
                        onClick = {
                            // ✅ NOTIFICACIÓN DE MODO INVITADO
                            NotificationHelper.showSimpleNotification(
                                context = context,
                                notificationId = 3, // ID Único
                                title = "Modo Invitado",
                                text = "Estás navegando como invitado."
                            )
                            onContinueAsGuest()
                        },
                        enabled = !isLoading
                    ) {
                        Text("Continuar como invitado")
                    }
                }
            }
        }
    }
}
