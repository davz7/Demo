// Navigation.kt (Código Corregido)

package mx.edu.utez.demo // Tu paquete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost // Import necesario
import androidx.navigation.compose.composable // Import necesario
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.ui.screens.VerificarCodigoScreen // Import de tu pantalla

@Composable
fun Navigation() { // Usaremos esta función para encapsular el controlador de navegación

    // 1. Crear el controlador de navegación
    val navController = rememberNavController()

    // 2. Definir el NavHost
    NavHost(
        navController = navController, // Pasamos el controlador creado
        startDestination = "verificar_codigo", // Define la ruta inicial
        modifier = Modifier // Es buena práctica pasar un modifier, aunque sea vacío
    ) {
        // 3. Definir la ruta "verificar_codigo"
        composable("verificar_codigo") {
            // 4. Llamar a la pantalla composable dentro del lambda
            VerificarCodigoScreen(navController = navController)
        }

        // Aquí irían otras rutas como "login", "home", etc.
        // composable("login") { LoginScreen(navController) }
    }
}