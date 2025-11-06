package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.edu.utez.demo.data.model.Post

class EditarPostScreenViewModel : ViewModel(){
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    init {
        // Aquí simulas la carga de datos, en un caso real los datos vendrían de una base de datos o API
        _posts.value = listOf()
    }
}