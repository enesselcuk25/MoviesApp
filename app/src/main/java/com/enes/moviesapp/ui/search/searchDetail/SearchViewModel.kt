package com.enes.moviesapp.ui.search.searchDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _search: MutableLiveData<List<ResultDetails>> = MutableLiveData()
    val search: LiveData<List<ResultDetails>>
        get() = _search

    fun setDataSearch(result: List<ResultDetails>) {
        _search.postValue(result)
    }
}