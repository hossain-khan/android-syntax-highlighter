package dev.hossain.ynaash.example

import android.app.Application
import timber.log.Timber

class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}