package com.enes.moviesapp.adapter.likedAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.databinding.ItemLikedViewBinding

class LikedHolder(private val binding: ItemLikedViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun likedBind(
        moviesEntity: MovieFavoriteEntity,
        moviesList: (MovieFavoriteEntity, View) -> Unit
    ) {
        binding.movieLiked = moviesEntity

        binding.root.setOnClickListener {
            moviesList.invoke(moviesEntity, it)
        }
    }
}