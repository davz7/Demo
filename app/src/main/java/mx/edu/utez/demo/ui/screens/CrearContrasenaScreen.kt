package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.demo.ui.components.buttons.AccionButton
import mx.edu.utez.demo.viewmodel.CrearContrasenaViewModel

@Composable
fun CrearContrasenaScreen(
    navController: NavController,
    viewModel: CrearContrasenaViewModel = viewModel()
) {
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    // Control de navegación después del éxito
    LaunchedEffect(uiState) {
        if (uiState == CrearContrasenaViewModel.UiState.Success) {
            // Navega a la pantalla de inicio de sesión o principal
            navController.navigate("login_screen") {
                // Limpia la pila para que no pueda volver a esta pantalla
                popUpTo(navController.graph.id) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        // --- Título ---
        Text(
            text = "Crea una contraseña nueva",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 64.dp)
        )
        Spacer(Modifier.height(48.dp))

        // --- Campo de Contraseña ---
        OutlinedTextField( // O usa tu componente SimpleTextField
            value = password,
            onValueChange = viewModel::updatePassword,
            label = { Text("Ingresa tu contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        // --- Campo de Confirmación ---
        OutlinedTextField( // O usa tu componente SimpleTextField
            value = confirmPassword,
            onValueChange = viewModel::updateConfirmPassword,
            label = { Text("Confirma tu contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))

        // --- Mensaje de Error/Carga ---
        when (val state = uiState) {
            is CrearContrasenaViewModel.UiState.Error -> {
                Text(state.message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
            }
            is CrearContrasenaViewModel.UiState.Loading -> {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
            else -> Spacer(Modifier.height(40.dp))
        }

        // --- Botón ---
        AccionButton(
            text = if (uiState == CrearContrasenaViewModel.UiState.Loading) "Cambiando..." else "Cambiar contraseña",
            onClick = viewModel::changePassword,
            enabled = password.isNotBlank() && confirmPassword.isNotBlank() && uiState != CrearContrasenaViewModel.UiState.Loading
        )
    }
}