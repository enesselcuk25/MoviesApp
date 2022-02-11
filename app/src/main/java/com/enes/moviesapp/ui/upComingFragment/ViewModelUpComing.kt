package com.enes.moviesapp.ui.upComingFragment

import androidx.lifecycle.*
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.base.BaseViewModel
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class ViewModelUpComing @Inject constructor
    (private val moviesRepository: MoviesRepository
    ): BaseViewModel() {

    init {
        getMoviesList()
    }

    private var mutableMoviesList: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val liveMoviesList: LiveData<List<MoviesList>>
        get() = mutableMoviesList

    private fun getMoviesList(){
        viewModelScope.launch {
            val result = moviesRepository.getUpcomingMovieList()
            when(result){
                is Resource.Success -> {
                    result.data?.results?.let {
                        val moviesList = result.data.results.mapIndexed{_,data ->
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

                        mutableMoviesList.postValue(moviesList)
                    }
                }
                is Resource.Error -> {

                }

            }
        }
    }

}