package mx.edu.utez.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost // Import necesario
import androidx.navigation.compose.composable // Import necesario
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.ui.screens.CrearContrasenaScreen
import mx.edu.utez.demo.ui.screens.CrearCuentaScreen
import mx.edu.utez.demo.ui.screens.HomeScreen
import mx.edu.utez.demo.ui.screens.EditarPublicacionScreen
import mx.edu.utez.demo.ui.screens.CrearPublicacionScreen
import mx.edu.utez.demo.ui.screens.LoginScreen
import mx.edu.utez.demo.ui.screens.RecuperarContrasenaScreen
import mx.edu.utez.demo.ui.screens.VerificarCodigoScreen // Import de tu pantalla
import mx.edu.utez.demo.viewmodel.LoginViewModel
import kotlin.js.ExperimentalJsFileName

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,startDestination = "login",
        modifier = Modifier
    ) {
        composable("login") {
            // Llamar a la pantalla composable dentro del lambda
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable("forgot_password") {
            RecuperarContrasenaScreen(navController)
        }
        composable("verificar_codigo") {
            // Llamar a la pantalla composable dentro del lambda
            VerificarCodigoScreen(navController = navController)
        }
        composable("crearContra") {
            // Llamar a la pantalla composable dentro del lambda
            CrearContrasenaScreen(navController = navController)
        }
        composable("crearCuenta") {
            // Llamar a la pantalla composable dentro del lambda
            CrearCuentaScreen(navController = navController)
        }
        composable("home") {
            // Llamar a la pantalla composable dentro del lambda
            HomeScreen(navController = navController)
        }
        composable("perfil") {
            // Llamar a la pantalla composable dentro del lambda
            EditarPublicacionScreen(navController = navController)
        }
        composable("crear") {
            // Llamar a la pantalla composable dentro del lambda
            CrearPublicacionScreen(navController = navController)
        }




    }
}

