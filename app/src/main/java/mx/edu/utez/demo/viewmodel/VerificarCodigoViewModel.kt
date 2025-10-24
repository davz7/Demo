package mx.edu.utez.demo.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerificarCodigoViewModel : ViewModel() {

    // 1. HARDCODE: El código correcto para la simulación
    private val CORRECT_CODE = "1234"

    // 2. Estado del campo de texto (lo manejamos aquí para que el ViewModel lo valide)
    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code

    // 3. Estado de la UI (para mostrar spinners o mensajes)
    private val _verificationState = MutableStateFlow<VerificationState>(VerificationState.Idle)
    val verificationState: StateFlow<VerificationState> = _verificationState

    // 4. Lógica para actualizar el código de entrada
    fun updateCode(newCode: String) {
        // Limitamos a 4 caracteres
        if (newCode.length <= 4) {
            _code.value = newCode
        }

        // Si el usuario empieza a escribir, limpiamos el estado de error/éxito anterior
        if (_verificationState.value is VerificationState.Error || _verificationState.value is VerificationState.Success) {
            _verificationState.value = VerificationState.Idle
        }
    }

    // 5. Lógica de verificación (simulada)
    fun verifyCode() {
        // Verificación inicial de longitud
        if (_code.value.length != 4) {
            _verificationState.value = VerificationState.Error("El código debe tener 4 dígitos.")
            return
        }

        // 5a. SIMULACIÓN DE CARGA (para mostrar un Spinner)
        _verificationState.value = VerificationState.Loading

        viewModelScope.launch {
            // SIMULACIÓN DE RETARDO DE RED (2 segundos)
            delay(2000)

            // 5b. LÓGICA HARDCODEADA: Comparar el código ingresado con el correcto
            if (_code.value == CORRECT_CODE) {
                // SIMULACIÓN DE ÉXITO
                _verificationState.value = VerificationState.Success
            } else {
                // SIMULACIÓN DE ERROR
                _verificationState.value = VerificationState.Error("El código ingresado es incorrecto.")
            }
        }
    }

    // 6. Función para resetear el estado después de una navegación o reintento
    fun resetState() {
        _code.value = ""
        _verificationState.value = VerificationState.Idle
    }
}


