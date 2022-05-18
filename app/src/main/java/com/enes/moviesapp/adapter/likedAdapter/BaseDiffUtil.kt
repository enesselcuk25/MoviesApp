package com.enes.moviesapp.adapter.likedAdapter

import androidx.recyclerview.widget.DiffUtil
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity


val BaseDiffUtil = object : DiffUtil.ItemCallback<MovieFavoriteEntity>() {
    override fun areItemsTheSame(
        oldItem: MovieFavoriteEntity,
        newItem: MovieFavoriteEntity,
    ): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(
        oldItem: MovieFavoriteEntity,
        newItem: MovieFavoriteEntity,
    ): Boolean = oldItem == newItem

}