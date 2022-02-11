package com.enes.moviesapp.adapter.recyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import com.enes.moviesapp.databinding.ItemAllmoviesBinding

class RcAllMoviesAdapter(val constext: Context) :
    RecyclerView.Adapter<ViewHolder>() {

//    var moviesList = mutableListOf<MoviesList>()
//    fun setMovieList(NewMoviesList: List<MoviesList>) {
//        this.moviesList = NewMoviesList.toMutableList()
//        notifyDataSetChanged()
//    }

    var moviesList : List<MoviesList> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private lateinit var moviesClick: (MoviesList, View) -> Unit
    fun onClickItem(onClick: (MoviesList,View) -> Unit) {
        this.moviesClick = onClick
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemAllmoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesList = moviesList[position]
        holder.bind(moviesList, constext, moviesClick)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}