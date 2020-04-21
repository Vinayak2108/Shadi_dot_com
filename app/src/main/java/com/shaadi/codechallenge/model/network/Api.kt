package com.shaadi.codechallenge.model.network

import com.google.gson.GsonBuilder
import com.shaadi.codechallenge.model.localdb.UserResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException


private const val BASE_URL = "https://randomuser.me"

interface API{

    @GET("/api/")
    fun getUser(@Query("results") result:Int = 10): Call<UserResponse>
}

object APIProvider{
    val api : API by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        APICreator(API::class.java).create()
    }
}

class APICreator<out API>(private val clazz: Class<API>){
    internal fun create():API{

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        val client = httpClient.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(clazz)
    }
}