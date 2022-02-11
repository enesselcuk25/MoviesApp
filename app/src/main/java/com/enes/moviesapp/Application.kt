package com.enes.moviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {

//    private var networkModule: NetworkModule? = null

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

//        startKoin {
//            androidContext(this@Application)
//            modules(listOf(appModule, networkModule!!.newWorkModule))
//        }
    }
}