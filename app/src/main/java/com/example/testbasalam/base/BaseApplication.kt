package com.example.testbasalam.base


import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


import timber.log.Timber.DebugTree


@HiltAndroidApp
class BaseApplication :MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

}