package com.enes.moviesapp.ui.popular

import androidx.lifecycle.*
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.base.BaseViewModel
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPopular @Inject constructor (private val moviesRepository:MoviesRepository):
    BaseViewModel() {


    init {
        moviesList()
    }

    private val mutableMovieList: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val liveMovieList: LiveData<List<MoviesList>>
        get() = mutableMovieList

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData()
    val progressBar: LiveData<Boolean> = _progressBar


    fun progresBar(visibility: Boolean) {
        _progressBar.postValue(visibility)
    }

    var loading = MutableLiveData(false)
   private var error = MutableLiveData("")

    private fun moviesList() {
        viewModelScope.launch {
            val result = moviesRepository.getPopularMovieList()
            loading.value = true
            when (result) {
                is Resource.Success -> {
                    result.data?.results?.let {
                        val moviesList = result.data.results.mapIndexed { _, data ->
                            MoviesList(
                                data.id,
                                data.poster_path,
                                data.overview,
                                data.title,
                                data.vote_average,
                                data.vote_count,
                                data.backdrop_path
                            )
                        }
                        loading.value = false
                        error.value = ""
                        mutableMovieList.postValue(moviesList)
                    }
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = result.message
                }
            }
        }
    }
}