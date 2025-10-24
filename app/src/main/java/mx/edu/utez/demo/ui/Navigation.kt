// Navigation.kt (Código Corregido)

package mx.edu.utez.demo // Tu paquete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost // Import necesario
import androidx.navigation.compose.composable // Import necesario
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.ui.screens.CrearContrasenaScreen
import mx.edu.utez.demo.ui.screens.CrearCuentaScreen
import mx.edu.utez.demo.ui.screens.VerificarCodigoScreen // Import de tu pantalla

@Composable
fun Navigation() {


    val navController = rememberNavController()


    NavHost(navController = navController,startDestination = "verificar_codigo",
        modifier = Modifier
    ) {
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



    }
}