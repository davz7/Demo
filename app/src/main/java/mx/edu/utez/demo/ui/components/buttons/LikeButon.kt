package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.edu.utez.demo.R

@Composable
fun LikeButton() {
    val isLiked = remember { mutableStateOf(false) }

    IconButton(onClick = { isLiked.value = !isLiked.value }) {
        val likeIcon = if (isLiked.value) painterResource(id = R.drawable.likeazul) else painterResource(id = R.drawable.likegris)
        Icon(painter = likeIcon, contentDescription = "", modifier = Modifier.size(24.dp))
    }
}
