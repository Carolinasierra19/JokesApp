package com.example.chucknorrisjokesapp.database



import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteJokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(joke: FavoriteJoke)

    @Delete
    fun deleteFavorite(joke: FavoriteJoke)

    @Query("SELECT * FROM favorite_jokes")
    fun getAllFavorites(): LiveData<List<FavoriteJoke>>
}



