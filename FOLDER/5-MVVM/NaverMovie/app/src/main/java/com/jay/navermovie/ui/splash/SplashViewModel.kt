package com.jay.navermovie.ui.splash

import androidx.databinding.ObservableBoolean
import com.jay.navermovie.data.login.source.LoginRepository

class SplashViewModel(private val loginRepository: LoginRepository) {
    var goMovieSearch: ObservableBoolean = ObservableBoolean(false)
    var goLogin: ObservableBoolean = ObservableBoolean(false)

    fun doSplash() {
        if (loginRepository.autoLogin) {
            goMovieSearch.notifyChange()
        } else {
            goLogin.notifyChange()
        }
    }
}