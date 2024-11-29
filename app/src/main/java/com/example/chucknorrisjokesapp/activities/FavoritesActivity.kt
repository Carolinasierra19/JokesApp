package com.example.chucknorrisjokesapp.activities



import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokesapp.adapter.FavoritesAdapter
import com.example.chucknorrisjokesapp.database.AppDatabase
import com.example.chucknorrisjokesapp.databinding.ActivityFavoritesBinding
import com.example.chucknorrisjokesapp.repository.JokesRepository
import com.example.chucknorrisjokesapp.viewmodel.FavoritesViewModel
import com.example.chucknorrisjokesapp.viewmodel.JokesViewModelFactory

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var factory: JokesViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // esto lo pongo para comfigurar el repositorio y el factory
        val database = AppDatabase.getDatabase(this)
        val repository = JokesRepository(database.favoriteJokeDao())
        factory = JokesViewModelFactory(repository)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvFavorites.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        viewModel.favorites.observe(this) { favorites ->
            if (favorites.isNullOrEmpty()) {
                // Mostrar mensaje vacío
                binding.tvEmptyMessage.visibility = View.VISIBLE
                binding.rvFavorites.visibility = View.GONE
            } else {
                // Mostrar lista de favoritos
                binding.tvEmptyMessage.visibility = View.GONE
                binding.rvFavorites.visibility = View.VISIBLE
                binding.rvFavorites.adapter = FavoritesAdapter(favorites) { joke ->
                    viewModel.removeFavorite(joke) // Eliminar favorito
                }
            }
        }
    }

}
