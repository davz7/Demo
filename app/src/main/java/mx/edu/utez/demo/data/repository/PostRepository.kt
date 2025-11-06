package mx.edu.utez.demo.data.repository

import kotlinx.coroutines.flow.Flow
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.data.model.PostDao

class PostRepository(private val dao: PostDao) {
    val allPosts: Flow<List<Post>> = dao.getAll()

    suspend fun insert(post: Post) = dao.insert(post)
    suspend fun update(post: Post) = dao.update(post)
    fun getById(id: Int): Flow<Post?> = dao.getById(id)
    suspend fun deleteById(id: Int) = dao.deleteById(id)
}
