package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.edu.utez.demo.R
import mx.edu.utez.demo.data.model.Post

class HomeViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    init {
        // Aquí simulas la carga de datos, en un caso real los datos vendrían de una base de datos o API
        _posts.value = listOf(
            Post(1, "Juan Pérez", "Título 1", "Descripción de la publicación 1", "2025-10-24", "12:30 PM", R.drawable.gato),
            Post(2, "Ana Gómez", "Título 2", "Descripción de la publicación 2", "2025-10-23", "11:00 AM", R.drawable.perro),
            Post(3, "Carlos López", "Título 3", "Descripción de la publicación 3", "2025-10-22", "10:00 AM", R.drawable.manzana)
        )
    }
}

