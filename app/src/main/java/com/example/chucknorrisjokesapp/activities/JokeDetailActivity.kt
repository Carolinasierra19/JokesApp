package com.example.chucknorrisjokesapp.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.chucknorrisjokesapp.database.AppDatabase
import com.example.chucknorrisjokesapp.database.FavoriteJoke
import com.example.chucknorrisjokesapp.databinding.ActivityJokeDetailBinding
import com.example.chucknorrisjokesapp.repository.JokesRepository
import com.example.chucknorrisjokesapp.viewmodel.CategoriesViewModel
import com.example.chucknorrisjokesapp.viewmodel.JokesViewModelFactory

class JokeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailBinding
    private lateinit var factory: JokesViewModelFactory
    private val viewModel: CategoriesViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // para configurar el repositorio y el factory
        val database = AppDatabase.getDatabase(this)
        val repository = JokesRepository(database.favoriteJokeDao())
        factory = JokesViewModelFactory(repository)

        // Obtener la categoría seleccionada
        val category = intent.getStringExtra("CATEGORY") ?: return

        // Llamo al ViewModel para obtener un chiste de la categoría seleccionada
        viewModel.fetchJokeByCategory(category)

        // Observar los cambios en el ViewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.joke.observe(this) { joke ->
            binding.tvJokeText.text = joke.value
            Glide.with(this).load(joke.icon_url).into(binding.ivJokeIcon)

            // Configurar el botón de guardar en favoritos
            binding.btnSaveFavorite.setOnClickListener {
                val favoriteJoke = FavoriteJoke(
                    jokeId = joke.id,
                    jokeText = joke.value,
                    iconUrl = joke.icon_url
                )
                viewModel.addFavorite(favoriteJoke)
            }
        }
    }
}

