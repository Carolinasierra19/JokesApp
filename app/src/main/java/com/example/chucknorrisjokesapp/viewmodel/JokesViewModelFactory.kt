package com.example.chucknorrisjokesapp.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokesapp.repository.JokesRepository

class JokesViewModelFactory(private val repository: JokesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CategoriesViewModel::class.java) -> {
                CategoriesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
