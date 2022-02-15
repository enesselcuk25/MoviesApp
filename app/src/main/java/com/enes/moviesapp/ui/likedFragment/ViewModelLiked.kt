package com.enes.moviesapp.ui.likedFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.enes.moviesapp.base.BaseViewModel
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity
import com.enes.moviesapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelLiked @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    private val moviesMutableLiveData: MutableLiveData<List<MovieFavoriteEntity>> =
        MutableLiveData()
    val moviesLiveData: LiveData<List<MovieFavoriteEntity>>
        get() = moviesMutableLiveData

    init {
        getFavorite()
    }

     fun getFavorite() {
        viewModelScope.launch {
            val data = moviesRepository.getLikedMoviesInfo()

            data.let { moviesList ->
                val listMovies = arrayListOf<MovieFavoriteEntity>()
                moviesList?.forEach {
                    listMovies.add(
                        MovieFavoriteEntity(
                            it.id,
                            it.backdrop_path,
                            it.overview,
                            it.popularity,
                            it.poster_path,
                            it.title,
                            it.vote_average
                        )
                    )
                }
                moviesMutableLiveData.postValue(listMovies)
            }
        }
    }

}