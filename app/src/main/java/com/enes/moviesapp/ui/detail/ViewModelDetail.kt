package com.enes.moviesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.data.remote.model.MovieDetailList
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.base.BaseViewModel
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ViewModelDetail @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    private val moviesMutableLiveData: MutableLiveData<MovieDetailList> = MutableLiveData()
    val liveMovies:LiveData<MovieDetailList>
          get()=moviesMutableLiveData

    var loading = MutableLiveData(false)
    private var error = MutableLiveData("")

    fun fetchMoviesData(id:Int?){
        viewModelScope.launch {
            val result = moviesRepository.getMoviesInfo(id)
            loading.value = true
            when(result){
                is Resource.Success -> {
                    result.data?.let { movieList ->
                        moviesMutableLiveData.value = MovieDetailList(
                            movieList.id,
                            movieList.backdrop_path,
                            movieList.title,
                            movieList.vote_average,
                            movieList.vote_count,
                            movieList.overview,
                            movieList.poster_path,
                            movieList.release_date
                        )
                    }
                    loading.value = false
                    error.value = ""
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = result.message
                }
            }
        }
    }

    fun addMoviesFavorite(){
        moviesMutableLiveData.value?.let { moviesDetail ->
            viewModelScope.launch {
                moviesRepository.insertMovies(MovieFavoriteEntity(
                    moviesDetail.id,
                    moviesDetail.backdrop_path,
                    moviesDetail.overview,
                    moviesDetail.vote_average,
                    moviesDetail.poster_path,
                    moviesDetail.title,
                    moviesDetail.vote_average
                ))
            }
        }
    }

    fun getFavorite(id: Int?, isLiked: (Boolean) ->Unit){
        viewModelScope.launch {
            isLiked.invoke(moviesRepository.isMoviesFavorite(id))
        }
    }

    fun deleteFavori(id:Int?) {
        viewModelScope.launch {
            moviesRepository.deleteFavorite(id)
        }
    }

}