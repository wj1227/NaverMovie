package com.jay.navermovie.module

import com.jay.navermovie.data.search.source.remote.MovieRemoteDataSource
import com.jay.navermovie.data.search.source.remote.MovieRemoteDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataModule: Module = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}