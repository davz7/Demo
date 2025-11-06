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
import mx.edu.utez.demo.ui.screens.CrearPublicacionScreen
import mx.edu.utez.demo.ui.screens.EditarPostScreen
import mx.edu.utez.demo.ui.screens.LoginScreen
import mx.edu.utez.demo.ui.screens.PerfilScreen
import mx.edu.utez.demo.ui.screens.RecuperarContrasena
import mx.edu.utez.demo.ui.screens.VerificarCodigoScreen // Import de tu pantalla
import mx.edu.utez.demo.viewmodel.LoginViewModel
import mx.edu.utez.demo.viewmodel.PerfilViewModel
import mx.edu.utez.demo.viewmodel.RecuperarContrasenaViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,startDestination = "home",
        modifier = Modifier
    ) {
        composable("login") {
            // Llamar a la pantalla composable dentro del lambda
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable("forgot_password") {
            val viewModel: RecuperarContrasenaViewModel = viewModel()
            RecuperarContrasena(navController = navController, viewModel = viewModel)
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
            val viewModel: PerfilViewModel = viewModel()
            PerfilScreen(navController = navController, viewModel = viewModel)
        }
        composable("crear") {
            // Llamar a la pantalla composable dentro del lambda
            CrearPublicacionScreen(navController = navController)
        }

        composable("editar/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt() ?: 0
            EditarPostScreen(navController, postId)
        }

    }
}

