package mx.edu.utez.demo.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.ui.components.ContextMenu

@Composable
fun ImageGrid(
    posts: List<Post>,
    onPostOptionsClick: (Int) -> Unit,
    expandedMenuIndex: Int?,
    onDismissMenu: () -> Unit,
    navController: NavController,
    onEditPost: (Post) -> Unit,
    onDeletePost: (Post) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(posts) { index, post ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.LightGray)
            ) {
                // 🔹 Cargar imagen desde Flask o usar imagen por defecto
                val baseUrl = "http://10.0.2.2:5000/" // cambia por tu IP si usas dispositivo físico
                val imageUrl = post.imageUri?.let { baseUrl + it }

                if (imageUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = "Imagen de publicación",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Imagen por defecto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                if (expandedMenuIndex == index) {
                    ContextMenu(
                        onDismiss = onDismissMenu,
                        navController = navController,
                        post = post,
                        onEdit = { onEditPost(post) },
                        onDelete = { onDeletePost(post) }
                    )
                }

                IconButton(
                    onClick = { onPostOptionsClick(index) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Opciones", tint = Color.White)
                }
            }
        }
    }
}
