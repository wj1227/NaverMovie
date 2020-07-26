package com.jay.navermovie.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.navermovie.data.login.source.LoginRepository

class SplashViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _goMovieSearch: MutableLiveData<Unit> = MutableLiveData()
    private val _goLogin: MutableLiveData<Unit> = MutableLiveData()

    val goMovieSearch: LiveData<Unit> get() = _goMovieSearch
    val goLogin: LiveData<Unit> get() = _goLogin

    fun doSplash() {
        if (loginRepository.autoLogin) {
            _goMovieSearch.value = Unit
        } else {
            _goLogin.value = Unit
        }
    }
}