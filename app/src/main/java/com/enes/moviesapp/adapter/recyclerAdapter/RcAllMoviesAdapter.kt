package com.enes.moviesapp.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.R
import com.enes.moviesapp.base.BaseDiffUtil
import com.enes.moviesapp.data.remote.model.MoviesList


class RcAllMoviesAdapter :
    ListAdapter<MoviesList, ViewHolder>(BaseDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_allmovies,
            parent,
            false))
    }

    private lateinit var moviesClick: (MoviesList, View) -> Unit
    fun onClickItem(onCLick: (MoviesList, View) -> Unit) {
        this.moviesClick = onCLick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesPosition = getItem(position)
        holder.bind(moviesPosition, moviesClick)
    }

}