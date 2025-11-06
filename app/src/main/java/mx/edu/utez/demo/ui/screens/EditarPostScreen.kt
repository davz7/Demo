package mx.edu.utez.demo.ui.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import mx.edu.utez.demo.R
import mx.edu.utez.demo.viewmodel.PostViewModel
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPostScreen(
    navController: NavController,
    postId: Int,
    postViewModel: PostViewModel = viewModel()
) {
    val context = LocalContext.current
    val post by postViewModel.getPostById(postId).collectAsState(initial = null)

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Guardar imagen en caché temporal
    fun saveBitmapToCache(bitmap: Bitmap): Uri {
        val file = File(context.cacheDir, "photo_${System.currentTimeMillis()}.jpg")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()
        return Uri.fromFile(file)
    }

    // Cargar los datos existentes del post
    LaunchedEffect(post) {
        post?.let {
            title = TextFieldValue(it.title)
            description = TextFieldValue(it.description)
            selectedImageUri = it.imageUri?.let { uri -> Uri.parse(uri) }
        }
    }

    // Lanzadores de galería y cámara
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri; photoBitmap = null }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            if (bitmap != null) {
                photoBitmap = bitmap
                selectedImageUri = null
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar publicación", fontWeight = FontWeight.Normal) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Imagen actual
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        photoBitmap != null -> Image(
                            bitmap = photoBitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        selectedImageUri != null -> Image(
                            painter = rememberAsyncImagePainter(selectedImageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        post?.imageUri != null -> Image(
                            painter = rememberAsyncImagePainter(post!!.imageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        else -> Text("Sin imagen")
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = {
                                galleryLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("Subir nueva imagen", color = Color.Black)
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = { cameraLauncher.launch(null) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("Tomar foto o video", color = Color.Black)
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Editar título") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Editar descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = MaterialTheme.shapes.medium
                )
            }

            Button(
                onClick = {
                    post?.let { oldPost ->
                        val newImageUri = when {
                            photoBitmap != null -> saveBitmapToCache(photoBitmap!!).toString()
                            selectedImageUri != null -> selectedImageUri.toString()
                            else -> oldPost.imageUri
                        }

                        val updatedPost = oldPost.copy(
                            title = title.text,
                            description = description.text,
                            imageUri = newImageUri
                        )

                        postViewModel.updatePost(updatedPost)
                        navController.navigate("perfil") {
                            popUpTo("perfil") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCD4))
            ) {
                Text("Actualizar publicación", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
