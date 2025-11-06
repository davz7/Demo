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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import mx.edu.utez.demo.R
import mx.edu.utez.demo.ui.components.ContextMenu
import mx.edu.utez.demo.viewmodel.PostViewModel

@Composable
fun ImageGrid(
    onPostOptionsClick: (Int) -> Unit,
    expandedMenuIndex: Int?,
    onDismissMenu: () -> Unit,
    navController: NavController,
    viewModel: PostViewModel = viewModel()
) {
    val posts by viewModel.postsFlow.collectAsState()

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
                if (post.imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(post.imageUri),
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
                        postViewModel = viewModel
                    )
                }


                IconButton(
                    onClick = { onPostOptionsClick(index) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.MoreVert, "Opciones", tint = Color.White)
                }
            }
        }
    }
}
