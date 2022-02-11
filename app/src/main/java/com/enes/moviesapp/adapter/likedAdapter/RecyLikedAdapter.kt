package com.enes.moviesapp.adapter.likedAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.databinding.ItemLikedViewBinding

class RecyLikedAdapter(private val context: Context): RecyclerView.Adapter<LikedHolder>() {


//   private var movielist = mutableListOf<MovieFavoriteEntity>()
//    fun setMovieList(newMoviesList: List<MovieFavoriteEntity>){
//        this.movielist = newMoviesList.toMutableList()
//        notifyDataSetChanged()
//    }

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
        return LikedHolder(ItemLikedViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: LikedHolder, position: Int) {
        val moviePosition = movieList[position]
        holder.likedBind(moviePosition,context,movieOnClick)
    }

    override fun getItemCount(): Int = movieList.size




}