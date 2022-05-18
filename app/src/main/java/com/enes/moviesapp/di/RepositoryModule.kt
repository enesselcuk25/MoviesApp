package com.enes.moviesapp.di

import com.enes.moviesapp.data.local.service.MoviesDao
import com.enes.moviesapp.data.remote.apiService.MoviesApi
import com.enes.moviesapp.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: MoviesApi,
        dao: MoviesDao
    ) = MoviesRepository(api, dao)
}