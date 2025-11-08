package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.demo.ui.components.images.ImageGrid
import mx.edu.utez.demo.ui.components.buttons.BottomNavigationBar
import mx.edu.utez.demo.ui.components.InfoSection
import mx.edu.utez.demo.ui.components.TopAppBar
import mx.edu.utez.demo.viewmodel.PostViewModel

@Composable
fun PerfilScreen(
    navController: NavController,
    postViewModel: PostViewModel = viewModel()
) {
    val posts by postViewModel.posts.observeAsState(emptyList())
    var expandedMenuIndex by remember { mutableStateOf<Int?>(null) }

    // 🔹 Cargar publicaciones desde Flask
    LaunchedEffect(Unit) {
        postViewModel.loadPosts()
    }

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

            // 🔹 Mostrar solo las publicaciones del usuario actual (por ejemplo, "Davor")
            val userPosts = posts.filter { it.username == "Davor" }

            ImageGrid(
                posts = userPosts,
                onPostOptionsClick = { index -> expandedMenuIndex = index },
                expandedMenuIndex = expandedMenuIndex,
                onDismissMenu = { expandedMenuIndex = null },
                navController = navController,
                onEditPost = { post ->
                    navController.navigate("editar/${post.id}")
                },
                onDeletePost = { post ->
                    // 🔹 Implementaremos el DELETE en Flask más adelante
                    // TODO: Implementar postViewModel.deletePost(post.id)
                }
            )
        }
    }
}
