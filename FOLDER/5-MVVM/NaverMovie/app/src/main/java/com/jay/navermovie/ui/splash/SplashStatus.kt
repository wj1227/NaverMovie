package com.jay.navermovie.ui.splash

sealed class SplashStatus {
    object LOGIN : SplashStatus()
    object MOVIE : SplashStatus()
}