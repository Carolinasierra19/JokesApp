package com.example.chucknorrisjokesapp.database



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_jokes")
data class FavoriteJoke(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID autogenerado
    val jokeId: String, // ID del chiste
    val jokeText: String, // Texto del chiste
    val iconUrl: String // URL imagen
)

