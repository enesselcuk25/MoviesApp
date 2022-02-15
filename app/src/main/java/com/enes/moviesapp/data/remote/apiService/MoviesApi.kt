package com.enes.moviesapp.data.remote.apiService

import com.enes.moviesapp.BuildConfig
import com.enes.moviesapp.data.remote.response.movies.AllMoviesResponse
import com.enes.moviesapp.data.remote.response.moviesDetail.MoviesDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {
    @GET("3/movie/{movies}")
    suspend fun getMovies(
        @Path("movies") string: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ):AllMoviesResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMoviesId(
        @Path("movie_id") movie_id: Int?,
        @Query("language") language: String= "en-US",
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MoviesDetail

}