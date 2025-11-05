package mx.edu.utez.demo.data.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val imageUri: String? = null,
    @DrawableRes val profileImageRes: Int = mx.edu.utez.demo.R.drawable.usuario // default
)
