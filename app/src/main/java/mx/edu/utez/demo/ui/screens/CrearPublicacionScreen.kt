package mx.edu.utez.demo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.demo.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearPublicacionScreen(navController: NavHostController) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Publicación", fontWeight = FontWeight.Normal) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(26.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Contenido superior
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Sección de la imagen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Imagen de la publicación",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("Subir desde galeria", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("Tomar foto o video", color = Color.Black)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Campos de texto para editar
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Agregar título") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Agregar descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    shape = MaterialTheme.shapes.medium
                )
            }

            // Botones de acción inferiores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón Cancelar publicación
                OutlinedButton(
                    onClick = { navController.navigate("home") },  // Navigate to Home
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar publicación", color = Color.Black)
                }

                // Botón Publicar
                Button(
                    onClick = { navController.navigate("home") }, // Navigate to Home after publish
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE0E0E0), // Gris claro
                        contentColor = Color.Gray // Texto gris oscuro
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Publicar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrearPublicacionScreenPreview() {
    CrearPublicacionScreen(navController = rememberNavController())
}
