package com.jay.navermovie.ui.search

import com.jay.navermovie.data.search.Movie

sealed class MovieStatus {
    object Success : MovieStatus()
    object DataEmpty : MovieStatus()
    object QueryEmpty : MovieStatus()
    object NetworkError : MovieStatus()
}