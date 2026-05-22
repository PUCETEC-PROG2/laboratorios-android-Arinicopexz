package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.threadName
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.github.com"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor. Level.BODY
    }
    private val  httpClient= OkHttpClient.Builder()
        .addInterceptor (interceptor = logging)
        .addInterceptor { chain ->
            val token = BuildConfig.GITHUB_TOKEN
            println("GITHUB TOKEN: $token")

            val request = chain.request().newBuilder()
                .addHeader( name = "Authorization", value = "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl( baseUrl=BASE_URL )
            .client(httpClient)
            .addConverterFactory( factory = GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
//interceptor, analiza una llamada y agrega campos, entre ellos el token

