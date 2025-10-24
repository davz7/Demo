package mx.edu.utez.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.R
import mx.edu.utez.calculadoramvvm.ui.components.images.CircularImage
import mx.edu.utez.calculadoramvvm.ui.components.inputs.PasswordField
import mx.edu.utez.calculadoramvvm.ui.components.texts.Link
import mx.edu.utez.demo.ui.components.buttons.SecondaryButton
import mx.edu.utez.demo.ui.components.inputsimport.UserInputField
import mx.edu.utez.demo.ui.components.textsimport.Title
import mx.edu.utez.demo.viewmodel.LoginViewModel



@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        CircularImage(R.drawable.usuario)
        Title("Bark")

        UserInputField(
            viewModel = viewModel,
            label = "Usuario"
        )

        PasswordField(
            viewModel = viewModel,
            label = "Contraseña"
        )

        if (viewModel.loginError.value.isNotEmpty()) {
            Text(
                text = viewModel.loginError.value,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Link("Olvide mi contraseña") {
            navController.navigate("forgot_password")
        }

        SecondaryButton("Iniciar sesión") {
            viewModel.login {
                navController.navigate("menu") {
                    popUpTo("login") { inclusive = true } // Evita volver al login
                }
            }
        }

        Link("Crear una cuenta") {
            navController.navigate("crearCuenta")
        }

    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel()

    LoginScreen(
        viewModel = viewModel,
        navController = navController
    )
}
