package com.jay.navermovie.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.jay.navermovie.data.login.source.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) {

    var id: ObservableField<String> = ObservableField("")
    var pw: ObservableField<String> = ObservableField("")
    var pb: ObservableBoolean = ObservableBoolean(false)
    var isIdEmpty: ObservableField<Unit> = ObservableField()
    var isPwEmpty: ObservableField<Unit> = ObservableField()
    var loginErrorMsg: ObservableField<Unit> = ObservableField()
    var loginSuccessMsg: ObservableField<Unit> = ObservableField()

    companion object {
        private const val USER_ID = "1234"
        private const val USER_PW = "1234"
    }

    fun onLoginClick() {
        pb.set(true)
        val id = id.get().toString().trim()
        val pw = pw.get().toString().trim()

        if (id.isEmpty()) {
            pb.set(false)
            isIdEmpty.notifyChange()
        } else if (pw.isEmpty()) {
            pb.set(false)
            isPwEmpty.notifyChange()
        } else if (id != USER_ID || pw != USER_PW) {
            pb.set(false)
            loginErrorMsg.notifyChange()
        } else {
            pb.set(false)
            loginRepository.autoLogin = true
            loginSuccessMsg.notifyChange()
        }
    }
}