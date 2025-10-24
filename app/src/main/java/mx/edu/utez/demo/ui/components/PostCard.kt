package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.ui.theme.DemoTheme


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
                painter = painterResource(id = post.imageRes), // Replace with the actual image resource
                contentDescription = "Post image",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Use a single Row for both buttons (Like and Comment)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                LikeButton(isLiked = isLiked, onLikeClick = { isLiked = !isLiked })

                // Comment button
                Button(onClick = { /* Handle Comment action */ }) {
                    Text("Comment")
                }
            }
        }
    }
}

@Composable
fun LikeButton(isLiked: Boolean, onLikeClick: () -> Unit) {
    val likeIcon = if (isLiked) R.drawable.likeazul else R.drawable.likegris // Change the icon based on like state

    Button(
        onClick = onLikeClick,
        modifier = Modifier.fillMaxWidth(0.4f), // Adjust width to control the button's size
        contentPadding = PaddingValues(4.dp)
    ) {
        Image(
            painter = painterResource(id = likeIcon),
            contentDescription = "Like button",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(if (isLiked) "Liked" else "Like")
    }
}
