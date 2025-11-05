package mx.edu.utez.demo.data.repository

import kotlinx.coroutines.flow.Flow
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.data.model.PostDao

class PostRepository(private val dao: PostDao) {
    val allPosts: Flow<List<Post>> = dao.getAll()

    suspend fun insert(post: Post) = dao.insert(post)
    suspend fun update(post: Post) = dao.update(post)
    suspend fun delete(post: Post) = dao.delete(post)
}
