package com.jay.navermovie.ui.login

interface LoginContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun showIdEmptyError()
        fun showPwEmptyError()
        fun showInfoFail()
        fun successLogin()
    }

    interface Presenter {

        fun ingLogin(id: String, pw: String)
    }
}