package com.enes.moviesapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.enes.moviesapp.R
import com.enes.moviesapp.data.remote.response.movies.ResultDetails


class SearchAdapter( private val click: OnClick) :
    androidx.recyclerview.widget.ListAdapter<ResultDetails, SearchViewHolder>(
        BaseDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_search,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val moviesPosition = getItem(position)
        holder.bind(moviesPosition, click)
    }

    class OnClick(val onClick: (result: ResultDetails) -> Unit) {
        fun click(resultDetails: ResultDetails) = onClick(resultDetails)
    }

}