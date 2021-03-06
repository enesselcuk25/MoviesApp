package com.enes.moviesapp.ui.topRated

import androidx.lifecycle.*
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.base.BaseViewModel
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelTopMovies @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    init {
        getMoviesList()
    }

    private var mutableMoviesList: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val liveMoviesList: LiveData<List<MoviesList>>
        get() = mutableMoviesList

    var loading = MutableLiveData(false)
    private var error = MutableLiveData("")

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData()
    val progressBar: LiveData<Boolean> = _progressBar


    fun progresBar(visibility: Boolean) {
        _progressBar.postValue(visibility)
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            val result = moviesRepository.getTopMoviesList()
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
                        mutableMoviesList.postValue(moviesList)
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