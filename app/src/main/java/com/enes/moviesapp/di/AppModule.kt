package com.enes.moviesapp.di

import com.enes.moviesapp.BuildConfig.BASE_URL
import com.enes.moviesapp.data.local.service.MoviesDataBase
import com.enes.moviesapp.data.local.service.MoviesDao
import com.enes.moviesapp.data.remote.apiService.MoviesApi
import com.enes.moviesapp.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRepository(
        api:MoviesApi,
        dao: MoviesDao
    )=MoviesRepository(api,dao)

    @Singleton
    @Provides
    fun providesMoviesApi():MoviesApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun getMoviesDataBase(context: android.app.Application): MoviesDataBase {
        return MoviesDataBase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getMoviesDao(moviesDataBase: MoviesDataBase): MoviesDao {
        return moviesDataBase.moviesDao()
    }
}