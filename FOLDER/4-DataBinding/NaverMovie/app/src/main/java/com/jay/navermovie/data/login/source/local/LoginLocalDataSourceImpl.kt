package com.jay.navermovie.data.login.source.local

import com.jay.navermovie.utils.PreferenceManager

class LoginLocalDataSourceImpl(
    private val preferencesManager: PreferenceManager
) : LoginLocalDataSource {

    override var autoLogin: Boolean
        get() = preferencesManager.autoLogin
        set(value) {
            preferencesManager.autoLogin = value
        }
}