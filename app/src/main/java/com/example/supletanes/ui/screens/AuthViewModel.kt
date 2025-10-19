// Ruta: app/src/main/java/com/example/supletanes/ui/screens/AuthViewModel.kt
package com.example.supletanes.ui.screens // ✅ Paquete correcto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.supletanes.ui.screens.profile.UserProfile // ✅ Importación correcta del UserProfile

class AuthViewModel : ViewModel() {

    private val _userState = mutableStateOf<UserProfile?>(null)
    val userState: State<UserProfile?> = _userState

    fun login(username: String, email: String) {
        _userState.value = UserProfile(name = username, email = email)
    }

    fun logout() {
        _userState.value = null
    }

    /**
     * Actualiza el nombre del usuario en el estado actual.
     */
    fun updateUsername(newName: String) {
        _userState.value?.let { currentUser ->
            _userState.value = currentUser.copy(name = newName)
        }
    }
}
