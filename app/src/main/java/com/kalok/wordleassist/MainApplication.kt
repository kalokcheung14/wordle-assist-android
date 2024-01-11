package com.kalok.wordleassist

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Plant debug tree
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }

        // Start Koin for DI
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(appModule)
        }
    }
}