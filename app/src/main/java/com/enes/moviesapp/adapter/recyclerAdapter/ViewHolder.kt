package com.enes.moviesapp.adapter.recyclerAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.databinding.ItemAllmoviesBinding

class ViewHolder(private val binding: ItemAllmoviesBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(
        moviesList: MoviesList,
        moviesClick: (MoviesList,View) -> Unit
    ) {
        binding.movieList = moviesList
        binding.root.setOnClickListener {
            moviesClick.invoke(moviesList,it)
        }
    }
}