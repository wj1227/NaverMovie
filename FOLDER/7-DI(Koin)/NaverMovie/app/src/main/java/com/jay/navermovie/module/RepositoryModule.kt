package com.jay.navermovie.module

import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.search.source.MovieRepository
import com.jay.navermovie.data.search.source.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}