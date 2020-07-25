package com.jay.navermovie.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    companion object {
        private const val MOVIE_SEARCH_APP = "MOVIE_SEARCH_APP"
        const val AUTO_LOGIN_KEY = "AUTO_LOGIN_KEY"
    }

    private val autoLoginPref: SharedPreferences = context.getSharedPreferences(MOVIE_SEARCH_APP, Context.MODE_PRIVATE)

    var autoLogin: Boolean
        get() = autoLoginPref.getBoolean(AUTO_LOGIN_KEY, false)
        set(value) {
            val editor = autoLoginPref.edit()
                editor.putBoolean(AUTO_LOGIN_KEY, value).apply()
        }

}