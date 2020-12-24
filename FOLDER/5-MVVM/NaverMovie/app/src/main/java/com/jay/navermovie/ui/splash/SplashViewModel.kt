package com.jay.navermovie.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.navermovie.base.BaseVieModel
import com.jay.navermovie.data.login.source.LoginRepository

class SplashViewModel(private val loginRepository: LoginRepository) : BaseVieModel<SplashStatus> {
    private val splash = MutableLiveData<SplashStatus>()
    val splashStatus: LiveData<SplashStatus> get() = splash
    fun doSplash() {
        if(loginRepository.autoLogin) SplashStatus.MOVIE.updateStatus()
        else SplashStatus.LOGIN.updateStatus()
    }
    override fun SplashStatus.updateStatus() = splash.postValue(this)
}