package com.enes.moviesapp.repository

import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.data.local.service.MoviesDao
import com.enes.moviesapp.data.remote.apiService.MoviesApi
import com.enes.moviesapp.data.remote.response.movies.AllMoviesResponse
import com.enes.moviesapp.data.remote.response.moviesDetail.MoviesDetail
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val dao: MoviesDao
) {

    suspend fun getTopMoviesList(): Resource<AllMoviesResponse> {
        val response = try {
            moviesApi.getMovies("top_rated")

        } catch (ex: Exception) {
            return Resource.Error(ex.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getPopularMovieList(): Resource<AllMoviesResponse> {
        val response = try {
            moviesApi.getMovies("popular")

        } catch (ex: Exception) {
            return Resource.Error(ex.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getNowPlayMovieList(): Resource<AllMoviesResponse> {
        val response = try {
            moviesApi.getMovies("now_playing")

        } catch (ex: Exception) {
            return Resource.Error(ex.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getUpcomingMovieList(): Resource<AllMoviesResponse> {
        val response = try {
            moviesApi.getMovies("upcoming")

        } catch (ex: Exception) {
            return Resource.Error(ex.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getMoviesInfo(movie_id: Int?): Resource<MoviesDetail> {
        val response = try {
            moviesApi.getMoviesId(movie_id)
        } catch (ex: Exception) {
            return Resource.Error(ex.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun insertMovies(moviesEntity: MovieFavoriteEntity) {
        dao.insertMoviesFavorite(moviesEntity)
    }

    suspend fun isMoviesFavorite(id:Int?):Boolean {
        return dao.getMoviesFavoriteId(id)!=null
    }

   suspend fun getLikedMoviesInfo(): List<MovieFavoriteEntity>? {
        return dao.getAllMoviesFavorite()
    }

    suspend fun deleteFavorite(id:Int?){
        return dao.deleteMovies(id)
    }




}