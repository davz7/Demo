package mx.edu.utez.demo.ui.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home")},
            icon = { Icon(Icons.Default.Home, "Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("crear")},
            icon = {
                // El ícono de añadir estaba muy grande, lo ajusté a un tamaño más razonable.
                Icon(Icons.Default.AddCircle, "Añadir",
                    modifier = Modifier.size(40.dp))
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("perfil") },
            icon = { Icon(Icons.Default.Person, "Perfil") }
        )
    }
}