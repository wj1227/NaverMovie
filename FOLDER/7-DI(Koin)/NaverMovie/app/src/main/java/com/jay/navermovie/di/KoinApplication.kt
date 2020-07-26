package com.jay.navermovie.di

import android.app.Application
import com.jay.navermovie.module.*
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(
                apiModule,
                localDataModule,
                remoteDataModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}