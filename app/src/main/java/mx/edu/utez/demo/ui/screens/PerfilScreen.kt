package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import mx.edu.utez.demo.ui.components.images.ImageGrid
import mx.edu.utez.demo.ui.components.buttons.BottomNavigationBar
import mx.edu.utez.demo.ui.components.InfoSection
import mx.edu.utez.demo.ui.components.TopAppBar
import mx.edu.utez.demo.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(viewModel: PerfilViewModel, navController: NavController) {
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
            ImageGrid(
                onPostOptionsClick = { index -> expandedMenuIndex = index },
                expandedMenuIndex = expandedMenuIndex,
                onDismissMenu = { expandedMenuIndex = null },
                navController = navController
            )
        }
    }
}









