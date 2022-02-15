package com.enes.moviesapp.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.R
import com.enes.moviesapp.data.remote.model.MoviesList
import java.util.*
import kotlin.collections.ArrayList

class RcAllMoviesAdapter :
    RecyclerView.Adapter<ViewHolder>(),Filterable {

    var moviesArrayList = ArrayList<MoviesList>()

    var moviesList: List<MoviesList> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        moviesList = moviesArrayList
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

    override fun getFilter(): Filter {
        return object :Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                moviesList = if(charSearch.isNotEmpty()){
                    moviesArrayList
                }else{
                    val resultList : MutableList<MoviesList> = mutableListOf()
                    for (row in moviesList){
                        if(row.title.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                    filterResults.values = moviesList
                    return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
               moviesList = p1?.values as MutableList<MoviesList>
                notifyDataSetChanged()
            }

        }
    }
}