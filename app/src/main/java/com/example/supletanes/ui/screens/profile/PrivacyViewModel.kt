package com.example.supletanes.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PrivacyViewModel : ViewModel() {

    // --- INICIO DE LA MODIFICACIÓN ---

    // 1. Canal para enviar eventos de UI
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // --- FIN DE LA MODIFICACIÓN ---

    // Estado para la opción de privacidad
    var receivePromotions by mutableStateOf(true)
        private set

    fun onReceivePromotionsChange(newValue: Boolean) {
        receivePromotions = newValue

        viewModelScope.launch {
            // Lógica para guardar esta preferencia...
            println("Preferencia de promociones guardada: $newValue")

            // --- INICIO DE LA MODIFICACIÓN ---

            // 2. Enviar el evento para navegar hacia atrás
            _uiEvent.send(UiEvent.NavigateBack)

            // --- FIN DE LA MODIFICACIÓN ---
        }
    }

    // Clase sellada para definir los eventos
    sealed class UiEvent {
        object NavigateBack : UiEvent()
    }
}
