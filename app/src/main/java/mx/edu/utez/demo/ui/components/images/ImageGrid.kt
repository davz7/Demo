package mx.edu.utez.demo.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import mx.edu.utez.demo.R
import mx.edu.utez.demo.ui.components.ContextMenu

@Composable
fun ImageGrid(
    onPostOptionsClick: (Int) -> Unit,
    expandedMenuIndex: Int?,
    onDismissMenu: () -> Unit,
    navController: NavController
) {
    val posts = (0..8).toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(posts) { index, _ ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.post_image),
                    contentDescription = "Publicación $index",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                if (expandedMenuIndex == index) {
                    ContextMenu(onDismiss = onDismissMenu, navController = navController)
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
