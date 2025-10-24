package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CrearCuentaViewModel : ViewModel() {

    // Estados de los campos de entrada
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    // Estado de la UI (simulación de carga/error)
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    // Clase sellada simple para el estado de la UI
    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }

    // Funciones para actualizar los estados
    fun updateEmail(newEmail: String) { _email.value = newEmail }
    fun updatePassword(newPass: String) { _password.value = newPass }
    fun updateUsername(newUsername: String) { _username.value = newUsername }

    fun registerAccount() {
        val currentEmail = _email.value
        val currentPassword = _password.value
        val currentUsername = _username.value

        // Validación "simulada"
        if (currentEmail.isBlank() || currentPassword.isBlank() || currentUsername.isBlank()) {
            _uiState.value = UiState.Error("Todos los campos son obligatorios.")
            return
        }
        if (!currentEmail.contains("@")) {
            _uiState.value = UiState.Error("Introduce un correo electrónico válido.")
            return
        }

        _uiState.value = UiState.Loading

        viewModelScope.launch {
            delay(2000) // Simulación de retraso de red (2 segundos)

            // Simulación: Si la contraseña es "error", falla. Si no, éxito.
            if (currentPassword == "error") {
                _uiState.value = UiState.Error("El nombre de usuario ya está tomado.")
            } else {
                _uiState.value = UiState.Success
            }
        }
    }
}