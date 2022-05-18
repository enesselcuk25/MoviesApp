package com.enes.moviesapp.ui.search.adapter



import androidx.recyclerview.widget.RecyclerView
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import com.enes.moviesapp.databinding.ItemSearchBinding

class SearchViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ResultDetails, click: SearchAdapter.OnClick) {
        binding.onClick = click
        binding.moviesSearch = item
    }
}