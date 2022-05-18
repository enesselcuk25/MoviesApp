package com.enes.moviesapp.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enes.moviesapp.data.remote.response.movies.ResultDetails

val BaseDiffUtil = object : DiffUtil.ItemCallback<ResultDetails>() {
    override fun areItemsTheSame(oldItem: ResultDetails, newItem: ResultDetails): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: ResultDetails, newItem: ResultDetails): Boolean =
        oldItem == newItem

}