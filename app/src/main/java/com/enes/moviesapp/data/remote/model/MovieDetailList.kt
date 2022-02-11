package com.enes.moviesapp.data.remote.model

data class MovieDetailList (
    val id: Int,
    val backdrop_path: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val overview: String,
    val poster_path: String,
    val release_date:String
)

