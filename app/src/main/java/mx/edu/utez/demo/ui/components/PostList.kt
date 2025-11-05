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
fun PreviewPostList() {}

