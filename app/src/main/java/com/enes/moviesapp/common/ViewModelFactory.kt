package com.enes.moviesapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enes.moviesapp.repository.MoviesRepository
import com.enes.moviesapp.ui.detailFragment.ViewModelDetail
import com.enes.moviesapp.ui.topRatedFragment.ViewModelTopMovies
import com.enes.moviesapp.ui.nowPlayingFragment.ViewModelNowPlaying
import com.enes.moviesapp.ui.populerFragment.ViewModelPopular
import com.enes.moviesapp.ui.upComingFragment.ViewModelUpComing
import java.lang.IllegalArgumentException

class ViewModelFactory(private val moviesRepository: MoviesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ViewModelTopMovies::class.java)){
            return ViewModelTopMovies(moviesRepository) as T
        }
        else if(modelClass.isAssignableFrom(ViewModelPopular::class.java)){
            return ViewModelPopular(moviesRepository) as T
        }else if(modelClass.isAssignableFrom(ViewModelNowPlaying::class.java)){
            return ViewModelNowPlaying(moviesRepository) as T
        }else if(modelClass.isAssignableFrom(ViewModelUpComing::class.java)){
                return ViewModelUpComing(moviesRepository) as T
        }else if(modelClass.isAssignableFrom(ViewModelDetail::class.java)){
            return ViewModelDetail(moviesRepository) as T
        }
        throw IllegalArgumentException("error")

    }



}