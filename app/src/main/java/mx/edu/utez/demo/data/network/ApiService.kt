package mx.edu.utez.demo.data.network

import mx.edu.utez.demo.data.model.Post
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // 🔹 Obtener todos los posts
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    // 🔹 Crear un nuevo post con multipart
    @Multipart
    @POST("posts")
    fun createPost(
        @Part("username") username: RequestBody,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("date") date: RequestBody,
        @Part("time") time: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): Call<Map<String, Any>>

    @Multipart
    @PUT("posts/{id}")
    fun updatePost(
        @Path("id") id: Int,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): Call<Map<String, Any>>

}
