package mx.edu.utez.demo.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // ⚠️ Usa tu IP local si lo vas a probar desde un celular Android conectado a la misma red Wi-Fi
    // Ejemplo: "http://192.168.1.73:5000/"
    private const val BASE_URL = "http://10.0.2.2:5000/" // Para emulador Android Studio

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
