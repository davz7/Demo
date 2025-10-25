package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utez.demo.R
import mx.edu.utez.demo.viewmodel.LoginViewModel
import mx.edu.utez.demo.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(viewModel: PerfilViewModel, navController: NavController) {
    var expandedMenuIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = { ProfileTopAppBar(navController) },
        bottomBar = { ProfileBottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ProfileInfoSection()
            ImageGrid(
                onPostOptionsClick = { index -> expandedMenuIndex = index },
                expandedMenuIndex = expandedMenuIndex,
                onDismissMenu = { expandedMenuIndex = null },
                navController = navController // Pasamos el navController aquí
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) } // Estado para controlar el DropdownMenu

    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logoapp), // Cambia esto por tu logo
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) { // Abre y cierra el menú al hacer clic
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
            // Menú desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cierra el menú cuando se hace clic fuera
            ) {
                // Opción para cerrar sesión
                DropdownMenuItem(
                    text = { Text("Cerrar sesión") },
                    onClick = {
                        expanded = false
                        navController.navigate("login") // Navega a la pantalla de login
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun ProfileInfoSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            // Banner
            Image(

                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Banner del perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
            )

            // Foto de Perfil
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomStart)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre de usuario", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text("100 seguidores", fontSize = 14.sp)
            Text(" | ", fontSize = 14.sp, color = Color.Gray)
            Text("120 seguidos", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("Descripción", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        // El botón "Seguir" parece que tenía un padding incorrecto, lo ajusté para que ocupe todo el ancho.
        Button(
            onClick = { /* Acción de seguir */ },
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
            Text("Seguir", color = Color.Black)
        }
    }
}

@Composable
fun ImageGrid(
    onPostOptionsClick: (Int) -> Unit,
    expandedMenuIndex: Int?,
    onDismissMenu: () -> Unit,
    navController: NavController // Asegúrate de pasar el navController
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
                    PostContextMenu(onDismiss = onDismissMenu, navController = navController) // Se pasa navController
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


@Composable
fun PostContextMenu(
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


@Composable
fun ProfileBottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home")},
            icon = { Icon(Icons.Default.Home, "Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("crear")},
            icon = {
                // El ícono de añadir estaba muy grande, lo ajusté a un tamaño más razonable.
                Icon(Icons.Default.AddCircle, "Añadir",
                    modifier = Modifier.size(40.dp))
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = {  },
            icon = { Icon(Icons.Default.Person, "Perfil") }
        )
    }
}


