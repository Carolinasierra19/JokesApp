package com.example.chucknorrisjokesapp.repository

import com.example.chucknorrisjokesapp.database.FavoriteJoke
import com.example.chucknorrisjokesapp.database.FavoriteJokeDao
import com.example.chucknorrisjokesapp.model.JokeResponse
import com.example.chucknorrisjokesapp.network.RetrofitInstance
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class JokesRepository(private val favoriteJokeDao: FavoriteJokeDao) {

    // funcion asíncrona para insertar un chiste
    suspend fun addFavoriteJoke(joke: FavoriteJoke) {
        withContext(Dispatchers.IO) {
            favoriteJokeDao.insertFavorite(joke)
        }
    }

    // funcion asíncrona para eliminar un chiste
    suspend fun removeFavoriteJoke(joke: FavoriteJoke) {
        withContext(Dispatchers.IO) {
            favoriteJokeDao.deleteFavorite(joke)
        }
    }

    // Obtener todos los chistes favoritos
    fun getAllFavoriteJokes(): LiveData<List<FavoriteJoke>> {
        return favoriteJokeDao.getAllFavorites()
    }

    suspend fun getCategories(): List<String> {
        return RetrofitInstance.api.getCategories()
    }

    // para sacar un chiste por categoría desde la API
    suspend fun getJokeByCategory(category: String): JokeResponse {
        return RetrofitInstance.api.getJokeByCategory(category)
    }
}
