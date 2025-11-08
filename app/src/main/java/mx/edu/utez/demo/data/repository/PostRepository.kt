package mx.edu.utez.demo.data.repository

import android.util.Log
import mx.edu.utez.demo.data.model.Post
import mx.edu.utez.demo.data.network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PostRepository {

    // 🔹 Obtener lista de publicaciones
    fun getPosts(onResult: (List<Post>?) -> Unit) {
        val call = RetrofitInstance.api.getPosts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    Log.e("PostRepository", "Error GET: ${response.code()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("PostRepository", "Fallo GET: ${t.message}")
                onResult(null)
            }
        })
    }

    // 🔹 Crear una publicación nueva
    fun createPost(
        username: String,
        title: String,
        description: String,
        date: String,
        time: String,
        imageFile: File?,
        onResult: (Boolean) -> Unit
    ) {
        // Convertir datos a RequestBody
        val usernamePart = username.toRequestBody("text/plain".toMediaTypeOrNull())
        val titlePart = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionPart = description.toRequestBody("text/plain".toMediaTypeOrNull())
        val datePart = date.toRequestBody("text/plain".toMediaTypeOrNull())
        val timePart = time.toRequestBody("text/plain".toMediaTypeOrNull())

        // Si hay imagen, convertirla a Multipart
        val imagePart = imageFile?.let {
            val reqFile = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", it.name, reqFile)
        }

        val call = RetrofitInstance.api.createPost(
            usernamePart, titlePart, descriptionPart, datePart, timePart, imagePart
        )

        call.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                if (response.isSuccessful) {
                    Log.d("PostRepository", "✅ Post creado correctamente: ${response.body()}")
                    onResult(true)
                } else {
                    Log.e("PostRepository", "❌ Error POST: ${response.code()}")
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.e("PostRepository", "❌ Fallo POST: ${t.message}")
                onResult(false)
            }
        })
    }
    fun updatePost(
        id: Int,
        title: String,
        description: String,
        imageFile: File?,
        onResult: (Boolean) -> Unit
    ) {
        val titlePart = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionPart = description.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = imageFile?.let {
            val reqFile = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", it.name, reqFile)
        }

        val call = RetrofitInstance.api.updatePost(id, titlePart, descriptionPart, imagePart)
        call.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.e("PostRepository", "Error PUT: ${t.message}")
                onResult(false)
            }
        })
    }

}
