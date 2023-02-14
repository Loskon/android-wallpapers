package com.loskon.wallpapers

import android.app.Application
import com.loskon.features.categorylist.categoryListModule
import com.loskon.features.wallpaper.wallpaperModule
import com.loskon.features.wallpaperlist.wallpaperListModule
import com.loskon.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initializeKoin(this)
    }

    private fun initializeKoin(application: Application) {
        startKoin {
            androidContext(application)
            modules(listOf(networkModule, categoryListModule, wallpaperListModule, wallpaperModule))
        }
    }
}