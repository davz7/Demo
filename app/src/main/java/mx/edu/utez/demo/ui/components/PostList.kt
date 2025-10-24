package mx.edu.utez.demo.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.viewmodel.HomeViewModel

@Composable
fun PostList(posts: List<Post>) {
    LazyColumn {
        items(posts) { post ->
            PostCard(post = post)
        }
    }
}

@Preview
@Composable
fun PreviewPostList() {
    val posts = listOf(
        Post(1, "John Doe", "Title 1", "Description 1", "2025-10-24", "12:30 PM", R.drawable.gato),
        Post(2, "Jane Smith", "Title 2", "Description 2", "2025-10-23", "11:00 AM", R.drawable.perro),
        Post(3, "Tom Johnson", "Title 3", "Description 3", "2025-10-22", "10:00 AM", R.drawable.manzana)
    )
    PostList(posts = posts)
}
