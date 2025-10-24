package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post


@Composable
fun PostCard(post: Post) {
    var isLiked by remember { mutableStateOf(false) } // Track the like state

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // User image
                Image(
                    painter = painterResource(R.drawable.usuario),
                    contentDescription = "User image",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(post.username)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(post.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(post.description)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Barked: ${post.date} ${post.time} ")
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = post.imageRes),
                contentDescription = "Post image",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                LikeButton(isLiked = isLiked, onLikeClick = { isLiked = !isLiked })

                Button(onClick = {  }) {
                    Text("Comment")
                }
            }
        }
    }
}
