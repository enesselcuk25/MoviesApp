package com.enes.moviesapp.adapter.recyclerAdapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enes.moviesapp.BuildConfig
import com.enes.moviesapp.R
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import com.enes.moviesapp.databinding.ItemAllmoviesBinding

class ViewHolder(private val binding: ItemAllmoviesBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(
        moviesList: MoviesList,
        context: Context,
        moviesClick: (MoviesList,View) -> Unit
    ) {

        with(moviesList) {
            Glide.with(context)
                .load(BuildConfig.IMAGE_BASE_W500 + this.backdrop_path)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.imageAllMovies)

            binding.tViewAllMovies.text = this.title

//                binding.imageAllMovies.setOnClickListener {
//                    click.moviesClick(moviesList,adapterPosition)
//                }

            binding.root.setOnClickListener {
                moviesClick.invoke(moviesList,it)
            }

        }
    }
}