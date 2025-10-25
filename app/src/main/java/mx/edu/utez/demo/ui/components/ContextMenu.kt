package mx.edu.utez.demo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ContextMenu(
    onDismiss: () -> Unit,
    navController: NavController
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text("Editar publicación") },
            onClick = {
                onDismiss()
                navController.navigate("editar")
            },
            leadingIcon = { Icon(Icons.Default.Edit, null) }
        )
        DropdownMenuItem(
            text = { Text("Revisar estadísticas") },
            onClick = { onDismiss() },
            leadingIcon = { Icon(Icons.Filled.Analytics, null) }
        )
        DropdownMenuItem(
            text = { Text("Más...") },
            onClick = { onDismiss() },
            leadingIcon = { Icon(Icons.Default.Info, null) }
        )
    }
}

