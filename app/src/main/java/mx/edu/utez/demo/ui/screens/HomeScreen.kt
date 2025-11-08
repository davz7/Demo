package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import mx.edu.utez.demo.ui.components.PostList
import mx.edu.utez.demo.ui.components.buttons.BottomNavigationBar
import mx.edu.utez.demo.ui.components.TopAppBar
import mx.edu.utez.demo.viewmodel.PostViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun HomeScreen(navController: NavController, viewModel: PostViewModel = viewModel()) {
    val posts by viewModel.posts.observeAsState(emptyList())

    // 🔹 Cargar publicaciones desde Flask
    LaunchedEffect(Unit) {
        viewModel.loadPosts()
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
            Text(
                "¡Bienvenido a Bark!",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "¡Publica tu primer bark!",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🔹 Lista de publicaciones desde Flask
            PostList(posts)
        }
    }
}

@Preview(showBackground = true, name = "Vista Previa de HomeScreen")
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
