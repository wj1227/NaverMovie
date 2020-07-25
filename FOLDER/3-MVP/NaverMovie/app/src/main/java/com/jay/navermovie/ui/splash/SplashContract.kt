package com.jay.navermovie.ui.splash

interface SplashContract {

    interface View {

        fun goLogin()
        fun goMovieSearch()
        fun showAutoLogin()
    }

    interface Presenter
}