package com.jay.navermovie.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.navermovie.base.BaseVieModel
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.utils.PreferenceManager

class SplashViewModel(val preferenceManger:PreferenceManager) : BaseVieModel<SplashStatus> {
    private val status = MutableLiveData<SplashStatus>()
    val splashStatus: LiveData<SplashStatus> get() = status
    fun doSplash() {
        if(preferenceManger.autoLogin) SplashStatus.MOVIE.updateStatus()
        else SplashStatus.LOGIN.updateStatus()
    }
    override fun SplashStatus.updateStatus() = status.postValue(this)
}