package com.enes.moviesapp.data.remote.apiService

import com.enes.moviesapp.BuildConfig
import com.enes.moviesapp.data.remote.response.movies.AllMoviesResponse
import com.enes.moviesapp.data.remote.response.moviesDetail.MoviesDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://api.themoviedb.org/3/movie/550?api_key=7d779c1e7a202d2aa4e20eb90a67548e
// https://api.themoviedb.org/3/movie/top_rated?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US&page=1
// https://api.themoviedb.org/3/movie/popular?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US&page=1
// https://api.themoviedb.org/3/movie/now_playing?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US&page=1
// https://api.themoviedb.org/3/movie/upcoming?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US&page=1


interface MoviesApi {

    // https://api.themoviedb.org/3/movie/3?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US

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