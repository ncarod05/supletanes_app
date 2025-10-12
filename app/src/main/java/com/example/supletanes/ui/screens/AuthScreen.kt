// En: com/example/supletanes/ui/screens/AuthScreen.kt
package com.example.supletanes.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,
    onContinueAsGuest: () -> Unit,
    loginViewModel: LoginViewModel = viewModel() // 1. Inyectamos el ViewModel
) {
    // 2. Obtenemos el estado desde el ViewModel, en lugar de usar "remember" aquí
    val uiState = loginViewModel.uiState
    val context = LocalContext.current

    // 3. Este bloque se ejecuta cuando el login es exitoso
    LaunchedEffect(key1 = uiState.loginExitoso) {
        if (uiState.loginExitoso) {
            Toast.makeText(context, "¡Login exitoso!", Toast.LENGTH_SHORT).show()
            loginViewModel.resetLoginExitoso() // Limpiamos el estado
            onLoginSuccess() // Navegamos a la siguiente pantalla
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crea tu cuenta o inicia sesión",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))

        // 4. Conectamos el OutlinedTextField al ViewModel
        OutlinedTextField(
            value = uiState.email, // Lee el valor del estado
            onValueChange = { loginViewModel.onEmailChange(it) }, // Notifica al ViewModel del cambio
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            isError = uiState.errorEmail != null, // Se pone en rojo si hay un error
            supportingText = { // Muestra el mensaje de error debajo
                if (uiState.errorEmail != null) {
                    Text(text = uiState.errorEmail)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 5. Conectamos el OutlinedTextField de la contraseña al ViewModel
        OutlinedTextField(
            value = uiState.contrasena, // Lee el valor del estado
            onValueChange = { loginViewModel.onContrasenaChange(it) }, // Notifica al ViewModel
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            isError = uiState.errorContrasena != null, // Se pone en rojo si hay un error
            supportingText = { // Muestra el mensaje de error debajo
                if (uiState.errorContrasena != null) {
                    Text(text = uiState.errorContrasena)
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        // 6. El botón ahora llama a la función de validación del ViewModel
        Button(
            onClick = { loginViewModel.validarFormulario() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary // Fondo Azul Acero
            )
        ) {
            Text("Iniciar Sesión / Registrarse",
                color = MaterialTheme.colorScheme.onPrimary //BlancoPuro
                )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onContinueAsGuest,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar como invitado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    // La preview sigue funcionando sin cambios
    AuthScreen(onLoginSuccess = {}, onContinueAsGuest = {})
}
