package com.example.chucknorrisjokesapp.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object RetrofitInstance {

    private const val BASE_URL = "https://api.chucknorris.io/jokes/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

