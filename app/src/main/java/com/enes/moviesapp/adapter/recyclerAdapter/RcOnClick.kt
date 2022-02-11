package com.enes.moviesapp.adapter.recyclerAdapter

import com.enes.moviesapp.data.remote.response.movies.ResultDetails

interface RcOnClick {
    fun moviesClick(resultDetails: ResultDetails, pos:Int)
}