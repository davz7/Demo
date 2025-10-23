package mx.edu.utez.demo.viewmodel

sealed class VerificationState {
    // Estado inicial, esperando que el usuario ingrese el código
    object Idle : VerificationState()

    // El proceso de verificación está en curso
    object Loading : VerificationState()

    // El código es correcto (simulación hardcodeada)
    object Success : VerificationState()

    // El código es incorrecto o hubo un error (simulación hardcodeada)
    data class Error(val message: String) : VerificationState()
}