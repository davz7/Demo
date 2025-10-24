package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.demo.R
import mx.edu.utez.demo.ui.components.PostList
import mx.edu.utez.demo.viewmodel.HomeViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { ProfileTopAppBar1() },
        bottomBar = { ProfileBottomNavigationBar1(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text("¡Bienvenido a Bark", modifier = Modifier.align(Alignment.CenterHorizontally))
            Text("¡Publica tu primer bark!", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            PostList(posts)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar1() {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logoapp),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun ProfileBottomNavigationBar1(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, "Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("crear") },
            icon = {
                Icon(Icons.Default.AddCircle, "Añadir", modifier = Modifier.size(40.dp))
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("perfil") },
            icon = { Icon(Icons.Default.Person, "Perfil") }
        )
    }
}

@Preview(showBackground = true, name = "Vista Previa de HomeScreen")
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
