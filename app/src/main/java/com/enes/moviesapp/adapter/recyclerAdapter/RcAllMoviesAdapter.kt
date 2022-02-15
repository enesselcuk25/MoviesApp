package com.enes.moviesapp.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.R
import com.enes.moviesapp.data.remote.model.MoviesList


class RcAllMoviesAdapter :
    RecyclerView.Adapter<ViewHolder>() {



    var moviesList: List<MoviesList> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    private lateinit var moviesClick: (MoviesList, View) -> Unit
    fun onClickItem(onClick: (MoviesList, View) -> Unit) {
        this.moviesClick = onClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_allmovies,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesList = moviesList[position]
        holder.bind(moviesList, moviesClick)



    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}