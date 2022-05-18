package com.enes.moviesapp.data.remote.response.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dates(
    val maximum: String,
    val minimum: String,
) : Parcelable