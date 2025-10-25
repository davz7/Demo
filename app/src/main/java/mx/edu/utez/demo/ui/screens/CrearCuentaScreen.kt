package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.demo.R
import mx.edu.utez.demo.ui.components.buttons.AccionButton
import mx.edu.utez.demo.viewmodel.CrearCuentaViewModel

@Composable
fun CrearCuentaScreen(
    navController: NavController,
    viewModel: CrearCuentaViewModel = viewModel(),

) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val username by viewModel.username.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    // Control de navegación después del éxito
    LaunchedEffect(uiState) {
        if (uiState == CrearCuentaViewModel.UiState.Success) {
            // Navega a la pantalla de inicio de sesión o principal
            navController.navigate("login") {
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

        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(

                painter = painterResource(id = R.drawable.logoapp),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
        }

        // --- Título/Instrucción ---
        Text(
            text = "REGÍSTRATE",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(24.dp))

        // --- Campo Correo Electrónico ---
        OutlinedTextField(
            value = email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        // --- Campo Contraseña ---
        OutlinedTextField(
            value = password,
            onValueChange = viewModel::updatePassword,
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        // --- Campo Nombre de Usuario ---
        OutlinedTextField(
            value = username,
            onValueChange = viewModel::updateUsername,
            label = { Text("Nombre de usuario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))

        // --- Mensaje de Error/Carga ---
        when (val state = uiState) {
            is CrearCuentaViewModel.UiState.Error -> {
                Text(state.message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
            }
            is CrearCuentaViewModel.UiState.Loading -> {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
            else -> Spacer(Modifier.height(40.dp))
        }

        // --- Botón Crear Cuenta ---
        AccionButton(
            text = if (uiState == CrearCuentaViewModel.UiState.Loading) "Registrando..." else "Crear cuenta",
            onClick = viewModel::registerAccount,
            enabled = email.isNotBlank() && password.isNotBlank() && username.isNotBlank() && uiState != CrearCuentaViewModel.UiState.Loading
        )
        Spacer(Modifier.height(24.dp))

        // --- Opción Iniciar Sesión ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "¿Ya tienes cuenta? ",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "Iniciar sesión",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {

                    navController.navigate("login")
                }
            )
        }
    }
}