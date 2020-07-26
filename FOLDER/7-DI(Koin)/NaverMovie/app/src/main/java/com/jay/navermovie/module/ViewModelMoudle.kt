package com.jay.navermovie.module

import com.jay.navermovie.ui.login.LoginViewModel
import com.jay.navermovie.ui.search.MovieSearchViewModel
import com.jay.navermovie.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MovieSearchViewModel(get()) }
}