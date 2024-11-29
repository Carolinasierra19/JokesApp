package com.example.chucknorrisjokesapp.viewmodel

import com.example.chucknorrisjokesapp.database.FavoriteJoke
import com.example.chucknorrisjokesapp.repository.JokesRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch




class FavoritesViewModel(private val repository: JokesRepository) : ViewModel() {

    val favorites: LiveData<List<FavoriteJoke>> = repository.getAllFavoriteJokes()

    // Agregar un chiste a favoritos
    fun addFavorite(joke: FavoriteJoke) {
        viewModelScope.launch {
            repository.addFavoriteJoke(joke)
        }
    }

    // Eliminar un chiste de favoritos
    fun removeFavorite(joke: FavoriteJoke) {
        viewModelScope.launch {
            repository.removeFavoriteJoke(joke)
        }
    }
}
