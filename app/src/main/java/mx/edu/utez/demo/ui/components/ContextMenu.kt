package mx.edu.utez.demo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import mx.edu.utez.demo.data.model.Post

@Composable
fun ContextMenu(
    onDismiss: () -> Unit,
    navController: NavController,
    post: Post,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = true,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text("Editar publicación") },
            onClick = {
                onDismiss()
                onEdit()
            },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = "Editar") }
        )

        DropdownMenuItem(
            text = { Text("Eliminar publicación") },
            onClick = { showDeleteDialog = true },
            leadingIcon = { Icon(Icons.Default.Delete, contentDescription = "Eliminar") }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                onDismiss()
            },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de que quieres eliminar esta publicación?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDismiss()
                }) { Text("Cancelar") }
            }
        )
    }
}
