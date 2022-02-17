package com.enes.moviesapp.adapter.likedAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.R
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity

class RecyLikedAdapter: RecyclerView.Adapter<LikedHolder>() {

    var movieList : List<MovieFavoriteEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var movieOnClick: (MovieFavoriteEntity, View) -> Unit
    fun onCLik(newMoviesOnclick : (MovieFavoriteEntity, View) ->Unit){
        this.movieOnClick = newMoviesOnclick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedHolder {
        return LikedHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_liked_view,parent,false))
    }

    override fun onBindViewHolder(holder: LikedHolder, position: Int) {
        val moviePosition = movieList[position]
        holder.likedBind(moviePosition,movieOnClick)
    }

    override fun getItemCount(): Int = movieList.size
}