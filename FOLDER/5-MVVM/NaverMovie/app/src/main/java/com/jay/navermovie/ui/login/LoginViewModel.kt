package com.jay.navermovie.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.utils.USER_ID
import com.jay.navermovie.utils.USER_PW
import com.jay.navermovie.utils.getString

class LoginViewModel(private val loginRepository: LoginRepository) {
    var id: ObservableField<String> = ObservableField("")
    var pw: ObservableField<String> = ObservableField("")
    var pb: ObservableBoolean = ObservableBoolean(false)
    val login = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> get() = login

    private fun LoginStatus.updateStatus() = login.postValue(this)
    private fun idPwCheck(id: String, pw: String): Boolean = id != USER_ID || pw != USER_PW

    fun onLoginClick() {
        val id = id.getString()
        val pw = pw.getString()
        when (true) {
            id.isEmpty() -> LoginStatus.IdEmpty.updateStatus()
            pw.isEmpty() -> LoginStatus.PwEmpty.updateStatus()
            idPwCheck(id, pw) -> LoginStatus.LoginError.updateStatus()
            else -> LoginStatus.LoginSuccess.updateStatus()
        }
    }
}