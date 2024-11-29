package com.example.chucknorrisjokesapp.network



import com.example.chucknorrisjokesapp.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService{

    @GET("categories")
    suspend fun getCategories(): List<String>

    @GET("random")
    suspend fun getJokeByCategory(@Query("category") category: String): JokeResponse
}

