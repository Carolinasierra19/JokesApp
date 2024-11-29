package com.example.chucknorrisjokesapp.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.database.FavoriteJoke

class FavoritesAdapter(
    private val favorites: List<FavoriteJoke>, // Cambiado de MutableList a List
    private val onRemoveFavorite: (FavoriteJoke) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvJoke: TextView = itemView.findViewById(R.id.tvJoke)
        private val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        private val btnRemove: Button = itemView.findViewById(R.id.btnRemove)

        fun bind(joke: FavoriteJoke) {
            tvJoke.text = joke.jokeText
            Glide.with(itemView.context).load(joke.iconUrl).into(ivIcon)
            btnRemove.setOnClickListener {
                onRemoveFavorite(joke)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_favorite_joke,
            parent,
            false
        )
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size
}

