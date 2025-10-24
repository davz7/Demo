package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CrearContrasenaViewModel : ViewModel() {

    // Estados de las contraseñas
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    // Estado de la UI (similar a VerificationState)
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    // Clase sellada simple para el estado de la UI (se puede poner en un archivo aparte)
    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }

    fun updatePassword(newPass: String) {
        _password.value = newPass
    }

    fun updateConfirmPassword(newPass: String) {
        _confirmPassword.value = newPass
    }

    fun changePassword() {
        if (_password.value.length < 6) {
            _uiState.value = UiState.Error("La contraseña debe tener al menos 6 caracteres.")
            return
        }
        if (_password.value != _confirmPassword.value) {
            _uiState.value = UiState.Error("Las contraseñas no coinciden.")
            return
        }

        _uiState.value = UiState.Loading

        viewModelScope.launch {
            delay(1500) // Simulación de carga

            // Simulación: siempre es exitoso si las contraseñas coinciden
            _uiState.value = UiState.Success
        }
    }
}