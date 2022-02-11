package com.enes.moviesapp.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM moviesEntity")
    suspend fun getAllMoviesFavorite(): List<MovieFavoriteEntity>?

    @Query("SELECT * FROM moviesEntity WHERE id =:id")
    suspend fun getMoviesFavoriteId(id: Int?): MovieFavoriteEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertMoviesFavorite(moviesEntity: MovieFavoriteEntity)

    @Query("DELETE FROM moviesEntity WHERE id=:id")
    suspend fun deleteMovies(id: Int?)


}