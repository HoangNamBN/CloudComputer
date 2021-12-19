package com.cloudcomputer.kimychat

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.cloudcomputer.kimychat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class KimyChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@KimyChatApplication)
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR
            )
//            modules(persistenceModule)
//            modules(repositoryModule)
            modules(viewModelModule)
//            modules(networkModule)
        }
        Kotpref.init(this)
    }
}