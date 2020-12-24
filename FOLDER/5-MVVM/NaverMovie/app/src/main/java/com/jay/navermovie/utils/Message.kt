package com.jay.navermovie.utils

import com.jay.navermovie.R

enum class Message(val id: Int) {
    IDEMPTY_ERROR(R.string.login_id_empty),
    PWEMPTY_ERROR(R.string.login_pw_empty),
    LOGIN_ERROR(R.string.login_user_fail),
    NETWORK_ERROR(R.string.search_network_error_msg),
    SUCESS(R.string.search_success),
    EMPTY_RESULT(R.string.search_emptyMovies),
    EMPTY_QUERY(R.string.search_query_empty);
    fun message() = toString()
    override fun toString(): String {
        return MyApplication.applicationContext().getString(id)
    }
}