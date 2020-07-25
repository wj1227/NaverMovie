package com.jay.navermovie.ui.login

import com.jay.navermovie.data.login.source.LoginRepository

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginRepository: LoginRepository
) : LoginContract.Presenter {

    companion object {
        private const val USER_ID = "1234"
        private const val USER_PW = "1234"
    }

    override fun ingLogin(id: String, pw: String) {
        view.showLoading()

        if (id.isEmpty()) {
            view.showIdEmptyError()
        } else if (pw.isEmpty()) {
            view.showPwEmptyError()
        } else if (id != USER_ID || pw != USER_PW) {
            view.showInfoFail()
        } else {
            loginRepository.autoLogin = true
            view.successLogin()
        }

        view.hideLoading()
    }
}