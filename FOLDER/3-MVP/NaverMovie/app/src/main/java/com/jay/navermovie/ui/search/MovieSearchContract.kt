package com.jay.navermovie.ui.search

import com.jay.navermovie.data.search.Movie

interface MovieSearchContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun networkError()
        fun emptyMovies()
        fun showMessage()
        fun errorMessage(msg: String)
        fun successSearchMovies(moives: List<Movie>)
    }

    interface Presenter {

        fun searchMovie(query: String)
    }
}