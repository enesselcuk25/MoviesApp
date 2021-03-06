package com.enes.moviesapp.data.remote.response.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AllMoviesResponse(
    val page: Int,
    val results: List<ResultDetails>,
    val total_pages: Int,
    val total_results: Int,
    val dates: Dates,
) : Parcelable