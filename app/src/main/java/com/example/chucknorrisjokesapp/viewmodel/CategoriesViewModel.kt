package com.example.chucknorrisjokesapp.viewmodel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapp.database.FavoriteJoke
import com.example.chucknorrisjokesapp.model.JokeResponse
import com.example.chucknorrisjokesapp.repository.JokesRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: JokesRepository) : ViewModel() {

    val categories: MutableLiveData<List<String>> = MutableLiveData()
    val joke: MutableLiveData<JokeResponse> = MutableLiveData()

    // para agarrar categorías desde el repositorio
    fun fetchCategories() {
        viewModelScope.launch {
            val result = repository.getCategories()
            categories.postValue(result)
        }
    }

    // para agarrar un chiste por categoría desde el repositorio
    fun fetchJokeByCategory(category: String) {
        viewModelScope.launch {
            val result = repository.getJokeByCategory(category)
            joke.postValue(result)
        }
    }

    // Agregar un chiste a favoritos
    fun addFavorite(joke: FavoriteJoke) {
        viewModelScope.launch {
            repository.addFavoriteJoke(joke)
        }
    }
}

