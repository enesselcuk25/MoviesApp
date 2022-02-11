package com.enes.moviesapp.data.remote.model

data class MoviesList(
    val id :Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Long,
    val backdrop_path: String,
)
