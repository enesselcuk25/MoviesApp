package com.enes.moviesapp.di

import com.enes.moviesapp.data.local.service.MoviesDao
import com.enes.moviesapp.data.local.service.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun getMoviesDataBase(context: android.app.Application): MoviesDataBase {
        return MoviesDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getMoviesDao(moviesDataBase: MoviesDataBase): MoviesDao {
        return moviesDataBase.moviesDao()
    }
}