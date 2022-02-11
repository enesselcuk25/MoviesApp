package com.enes.moviesapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofitDatabase(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit
                    .Builder()
                    .baseUrl(com.enes.moviesapp.BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}