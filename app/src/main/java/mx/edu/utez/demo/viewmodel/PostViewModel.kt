package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.data.repository.PostRepository
import java.io.File

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    fun loadPosts() {
        repository.getPosts { result ->
            _posts.postValue(result ?: emptyList()) //
        }
    }


    fun createPost(
        username: String,
        title: String,
        description: String,
        date: String,
        time: String,
        imageFile: File?,
        onSuccess: (Boolean) -> Unit
    ) {
        repository.createPost(username, title, description, date, time, imageFile) { success ->
            if (success) loadPosts() // Recarga la lista después de crear
            onSuccess(success)
        }
    }
    fun updatePost(
        id: Int,
        title: String,
        description: String,
        imageFile: File?,
        onSuccess: (Boolean) -> Unit
    ) {
        repository.updatePost(id, title, description, imageFile) { success ->
            if (success) loadPosts() // recarga después de editar
            onSuccess(success)
        }
    }

}
