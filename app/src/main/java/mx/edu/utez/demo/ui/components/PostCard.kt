package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post

@Composable
fun PostCard(post: Post) {
    val baseUrl = "http://10.0.2.2:5000/" // 🔹 cambia según tu entorno
    val imageUrl = if (post.imageUri != null) baseUrl + post.imageUri else null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto de perfil del usuario",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )

                Column {
                    Text(post.username, style = MaterialTheme.typography.titleMedium)
                    Text("${post.date} ${post.time}", style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(post.title, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(4.dp))
            Text(post.description, style = MaterialTheme.typography.bodySmall)

            Spacer(Modifier.height(8.dp))

            // 🔹 Imagen desde Flask o imagen por defecto
            if (imageUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Imagen de publicación",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Imagen por defecto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
