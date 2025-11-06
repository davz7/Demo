package mx.edu.utez.demo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.viewmodel.PostViewModel

@Composable
fun ContextMenu(
    onDismiss: () -> Unit,
    navController: NavController,
    post: Post,
    postViewModel: PostViewModel

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
                navController.navigate("editar")
            },
            leadingIcon = { Icon(Icons.Default.Edit, null) }
        )
        DropdownMenuItem(
            text = { Text("Eliminar publicación") },
            onClick = {
                showDeleteDialog = true
            },
            leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) }
        )

        DropdownMenuItem(
            text = { Text("Más...") },
            onClick = { onDismiss() },
            leadingIcon = { Icon(Icons.Default.Info, null) }
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
                    postViewModel.eliminarById(post.id)
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

