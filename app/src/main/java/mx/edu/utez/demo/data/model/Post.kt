package mx.edu.utez.demo.data.model

import androidx.annotation.DrawableRes

data class Post(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    @DrawableRes val imageRes: Int // Updated to use @DrawableRes for image
)
