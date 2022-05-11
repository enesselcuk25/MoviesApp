package com.enes.moviesapp.base

import androidx.recyclerview.widget.DiffUtil
import com.enes.moviesapp.data.remote.model.MoviesList


val BaseDiffUtil = object : DiffUtil.ItemCallback<MoviesList>(){
    override fun areItemsTheSame(oldItem: MoviesList, newItem: MoviesList): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MoviesList, newItem: MoviesList): Boolean =
        oldItem == newItem
}