package com.enes.moviesapp.adapter.likedAdapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enes.moviesapp.BuildConfig
import com.enes.moviesapp.R
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.databinding.ItemLikedViewBinding

class LikedHolder(private val binding: ItemLikedViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun likedBind(
        moviesEntity: MovieFavoriteEntity,
        context: Context,
        moviesList: (MovieFavoriteEntity, View) -> Unit
    ) {
        Glide.with(context)
            .load(BuildConfig.IMAGE_BASE_W500 + moviesEntity.backdrop_path)
            .error(R.drawable.ic_baseline_error_24)
            .into(binding.imageAllMovies)

        binding.tViewAllMovies.text = moviesEntity.title

        binding.root.setOnClickListener {
            moviesList.invoke(moviesEntity, it)
        }
    }
}