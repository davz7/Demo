package mx.edu.utez.demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.edu.utez.demo.data.AppDatabase
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.data.repository.PostRepository

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository

    val postsFlow by lazy {
        repository.allPosts.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    init {
        val dao = AppDatabase.getDatabase(application).postDao()
        repository = PostRepository(dao)
    }

    fun insertar(post: Post) = viewModelScope.launch {
        repository.insert(post)
    }

    fun eliminar(post: Post) = viewModelScope.launch {
        repository.delete(post)
    }

    fun actualizar(post: Post) = viewModelScope.launch {
        repository.update(post)
    }
}
