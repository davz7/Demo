package mx.edu.utez.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.ui.screens.*
import mx.edu.utez.demo.viewmodel.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
    ) {
        composable("login") {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable("forgot_password") {
            val viewModel: RecuperarContrasenaViewModel = viewModel()
            RecuperarContrasena(navController = navController, viewModel = viewModel)
        }
        composable("verificar_codigo") { VerificarCodigoScreen(navController = navController) }
        composable("crearContra") { CrearContrasenaScreen(navController = navController) }
        composable("crearCuenta") { CrearCuentaScreen(navController = navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("perfil") {
            val postViewModel: PostViewModel = viewModel()
            PerfilScreen(navController = navController, postViewModel = postViewModel)
        }
        composable("crear") { CrearPublicacionScreen(navController = navController) }

        // 🔹 Nueva navegación con objeto Post codificado como JSON
        composable(
            route = "editar/{postJson}",
            arguments = listOf(navArgument("postJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val postJson = backStackEntry.arguments?.getString("postJson")
            val post = Json.decodeFromString<Post>(postJson!!)
            EditarPostScreen(navController = navController, post = post)
        }
    }
}
