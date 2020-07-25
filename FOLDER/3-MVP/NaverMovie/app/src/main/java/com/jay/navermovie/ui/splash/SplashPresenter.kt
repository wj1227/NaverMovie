package com.jay.navermovie.ui.splash

import com.jay.navermovie.data.login.source.LoginRepository

class SplashPresenter(
    private val view: SplashContract.View,
    private val loginRepository: LoginRepository
) : SplashContract.Presenter {

    init {
        ingSplash()
    }

    private fun ingSplash() {
        if (loginRepository.autoLogin) {
            view.showAutoLogin()
            view.goMovieSearch()
        } else {
            view.goLogin()
        }
    }
}