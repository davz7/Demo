package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.utez.demo.ui.components.buttons.PrimaryButton

//Recuperar Contraseña Screen

@Composable
fun RecuperarContrasenaScreen(navController: NavController) {
    // Implementación de la pantalla de recuperar contraseña

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla de Recuperación de Contraseña")
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Volver al Login") {
            navController.popBackStack()
        }
    }
}