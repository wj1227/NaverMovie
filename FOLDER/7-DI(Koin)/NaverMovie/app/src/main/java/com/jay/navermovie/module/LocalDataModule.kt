package com.jay.navermovie.module

import androidx.room.Room
import com.jay.navermovie.data.database.MovieDao
import com.jay.navermovie.data.database.MovieDatabase
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.data.search.source.local.MovieLocalDataSource
import com.jay.navermovie.data.search.source.local.MovieLocalDataSourceImpl
import com.jay.navermovie.utils.PreferenceManager
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataModule: Module = module {
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }

    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }

    single { PreferenceManager((get())) }

    single { get<MovieDatabase>().movieDao() }

    single {
        Room.databaseBuilder(
            get(),
            MovieDatabase::class.java,
            "jay_db"
        )
            .allowMainThreadQueries()
            .build()
    }
}