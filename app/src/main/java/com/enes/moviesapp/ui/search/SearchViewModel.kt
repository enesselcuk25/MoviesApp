package com.enes.moviesapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enes.moviesapp.data.remote.response.movies.AllMoviesResponse
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _search: MutableLiveData<Resource<AllMoviesResponse>> = MutableLiveData()
    val search: LiveData<Resource<AllMoviesResponse>> = _search

    private val _navigate: MutableLiveData<ResultDetails> = MutableLiveData()
    val navigate: LiveData<ResultDetails> = _navigate


    fun setNavigate(naviagte: ResultDetails) {
        _navigate.postValue(naviagte)
    }


    fun getSearch(searchName: String) {
        viewModelScope.launch {
            val search = repository.search(searchName)

            _search.postValue(handleSearchNewsResponse(search))
        }
    }

    private fun handleSearchNewsResponse(movies: Response<AllMoviesResponse>): Resource<AllMoviesResponse> {
        if (movies.isSuccessful) {
            movies.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, movies.message())
    }
}