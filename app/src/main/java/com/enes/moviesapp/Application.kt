package com.enes.moviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}