package mx.edu.utez.demo.ui.screens


import mx.edu.utez.demo.viewmodel.VerificarCodigoViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.demo.ui.components.buttons.PrimaryButton

import mx.edu.utez.demo.ui.components.inputs.CodeInputBoxes
import mx.edu.utez.demo.viewmodel.VerificationState


@Composable
fun VerificarCodigoScreen(
    navController: NavController,
    viewModel: VerificarCodigoViewModel = viewModel()
) {
    val code by viewModel.code.collectAsState()
    val verificationState by viewModel.verificationState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Título y Subtítulo ---
        Text(
            text = "Verificar Código",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 64.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Por favor ingresa el código enviado al correo",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(Modifier.height(48.dp))


        // --- Componente de Cajas de Entrada  ---
        CodeInputBoxes(
            code = code,
            onCodeChange = viewModel::updateCode,
            boxCount = 4 // Usamos 4 cajas
        )
        Spacer(Modifier.height(16.dp))


        // --- Lógica de Estado (Éxito/Error/Carga) ---
        when (val state = verificationState) {
            is VerificationState.Loading -> {
                // Indicador de carga cuando está verificando
                CircularProgressIndicator(Modifier.size(24.dp))
            }
            is VerificationState.Error -> {
                // Mensaje de error (por ejemplo, "Código incorrecto")
                Text(state.message, color = Color.Red, modifier = Modifier.padding(8.dp))
            }
            is VerificationState.Success -> {
                // Mensaje de éxito y lógica de navegación
                Text("Código verificado correctamente.", color = Color.Green, modifier = Modifier.padding(8.dp))
                // TODO: Aquí deberías navegar al siguiente destino
                navController.navigate("crearContra")
            }
            else -> Spacer(Modifier.height(40.dp)) // Espacio si no hay mensaje
        }


        // --- Botón de Verificación  ---
        PrimaryButton(
            text = when (verificationState) {
                VerificationState.Loading -> "Verificando..."
                else -> "Verificar"
            },
            onClick = viewModel::verifyCode,
            // Habilitado si hay 4 dígitos y no está cargando
            enabled = code.length == 4 && verificationState != VerificationState.Loading
        )
        Spacer(Modifier.height(24.dp))


        // --- Opción Reenviar Código ---
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿No recibiste el código? ",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "Reenviar código",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // TODO: Implementar la lógica de reenvío
                }
            )
        }
    }
}