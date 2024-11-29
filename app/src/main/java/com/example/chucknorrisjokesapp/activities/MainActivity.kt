package com.example.chucknorrisjokesapp.activities



import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokesapp.adapter.CategoriesAdapter
import com.example.chucknorrisjokesapp.database.AppDatabase
import com.example.chucknorrisjokesapp.databinding.ActivityMainBinding
import com.example.chucknorrisjokesapp.repository.JokesRepository
import com.example.chucknorrisjokesapp.viewmodel.CategoriesViewModel
import com.example.chucknorrisjokesapp.viewmodel.JokesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: JokesViewModelFactory
    private val viewModel: CategoriesViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // para configurar el repositorio y el factory
        val database = AppDatabase.getDatabase(this)
        val repository = JokesRepository(database.favoriteJokeDao())
        factory = JokesViewModelFactory(repository)

        setupRecyclerView()
        setupButtons()
        observeViewModel()

        // Cargar las categorías al iniciar
        viewModel.fetchCategories()
    }

    private fun setupRecyclerView() {
        binding.rvCategories.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButtons() {
        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(this) { categories ->
            val adapter = CategoriesAdapter(categories) { category ->
                // Al hacer clic en una categoría, ir a JokeDetailActivity
                val intent = Intent(this, JokeDetailActivity::class.java)
                intent.putExtra("CATEGORY", category)
                startActivity(intent)
            }
            binding.rvCategories.adapter = adapter
        }
    }
}
