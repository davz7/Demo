package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.edu.utez.demo.R

@Composable
fun LikeButton(isLiked: Boolean, onLikeClick: () -> Unit) {
    val likeIcon = if (isLiked) R.drawable.likeazul else R.drawable.likegris

    Button(
        onClick = onLikeClick,
        modifier = Modifier.fillMaxWidth(0.4f),
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
