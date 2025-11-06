package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.demo.ui.components.images.ImageGrid
import mx.edu.utez.demo.ui.components.buttons.BottomNavigationBar
import mx.edu.utez.demo.ui.components.InfoSection
import mx.edu.utez.demo.ui.components.TopAppBar
import mx.edu.utez.demo.viewmodel.PerfilViewModel
import mx.edu.utez.demo.viewmodel.PostViewModel

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel,
    navController: NavController,
    postViewModel: PostViewModel = viewModel()
) {
    // Cargar publicaciones reales desde SQLite
    val posts by postViewModel.postsFlow.collectAsState(initial = emptyList())

    var expandedMenuIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = { TopAppBar(navController) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            InfoSection()

            // Grid que mostrará las publicaciones reales
            ImageGrid(
                posts = posts,
                onPostOptionsClick = { index -> expandedMenuIndex = index },
                expandedMenuIndex = expandedMenuIndex,
                onDismissMenu = { expandedMenuIndex = null },
                navController = navController,
                onDeletePost = { post ->
                    postViewModel.deleteById(post.id)
                },
                onEditPost = { post ->
                    navController.navigate("editar/${post.id}")
                }
            )
        }
    }
}
