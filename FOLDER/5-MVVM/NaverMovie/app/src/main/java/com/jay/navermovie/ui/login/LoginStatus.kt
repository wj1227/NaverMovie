package com.jay.navermovie.ui.login

sealed class LoginStatus {
    object IdEmpty : LoginStatus()
    object PwEmpty : LoginStatus()
    object LoginError : LoginStatus()
    object LoginSuccess : LoginStatus()
}