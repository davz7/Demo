package mx.edu.utez.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.ui.components.buttons.SecondaryButton
import mx.edu.utez.calculadoramvvm.ui.components.images.CircularImage
import mx.edu.utez.demo.R
import mx.edu.utez.demo.viewmodel.RecuperarContrasenaViewModel
import androidx.compose.material3.*
import mx.edu.utez.demo.ui.components.inputsimport.CorreoInputField
import mx.edu.utez.demo.ui.components.textsimport.Title

@Composable
fun RecuperarContrasena(viewModel: RecuperarContrasenaViewModel, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top =250.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Title(
            text = "Recuperar Contraseña",
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        CircularImage(R.drawable.usuario)

        Spacer(modifier = Modifier.height(24.dp))

        CorreoInputField(
            viewModel = viewModel,
            label = "Correo Electrónico"
        )

        if (viewModel.loginError.value.isNotEmpty()) {
            Text(
                text = viewModel.loginError.value,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryButton("Enviar código") {
            viewModel.login {
                navController.navigate("login") {

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryButton("Volver al Login") {
            navController.popBackStack()
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewRecuperarContrasena() {
    val navController = rememberNavController()
    val viewModel = RecuperarContrasenaViewModel()

    RecuperarContrasena(
        viewModel = viewModel,
        navController = navController
    )
}