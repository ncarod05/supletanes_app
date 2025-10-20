package com.example.supletanes.ui.screens.auth.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val contrasena: String = "",
    val errorEmail: String? = null,
    val errorContrasena: String? = null,
    val loginExitoso: Boolean = false
)

class LoginViewModel : ViewModel() {

    // 'var' para poder modificar el estado directamente
    var uiState by mutableStateOf(LoginUiState())
        private set

    //Función para actualizar el estado desde la UI
    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email, errorEmail = null) // Limpia el error al escribir
    }

    fun onContrasenaChange(contrasena: String) {
        uiState = uiState.copy(contrasena = contrasena, errorContrasena = null) // Limpia el error al escribir
    }

    //Función que contiene la lógica de validación
    fun validarFormulario() {
        val emailValido = Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()
        val contrasenaValida = uiState.contrasena.length >= 6

        //Actualizar el estado con los errores correspondientes
        uiState = uiState.copy(
            errorEmail = if (!emailValido) "El correo no es válido" else null,
            errorContrasena = if (!contrasenaValida) "La contraseña debe tener al menos 6 caracteres" else null
        )

        //Si ambos son válidos, procedemos a "iniciar sesión"
        if (emailValido && contrasenaValida) {
            iniciarSesion()
        }
    }

    private fun iniciarSesion() {
        //Usamos viewModelScope para operaciones asíncronas.
        viewModelScope.launch {
            // Simulamos una llamada de red
            delay(1500)

            // Actualizamos el estado para indicar que el login fue exitoso
            uiState = uiState.copy(loginExitoso = true)
        }
    }

    //Función para resetear el estado de loginExitoso después de navegar
    fun resetLoginExitoso() {
        uiState = uiState.copy(loginExitoso = false)
    }
}


