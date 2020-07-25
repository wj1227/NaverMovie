package com.jay.navermovie.ui.search

import com.jay.navermovie.data.search.source.MovieRepository
import retrofit2.HttpException

class MovieSearchPresenter(
    private val view: MovieSearchContract.View,
    private val movieRepository: MovieRepository
) : MovieSearchContract.Presenter {

    override fun searchMovie(query: String) {
        if (query.isEmpty()) {
            view.showMessage()
        } else {
            view.showLoading()
            movieRepository.searchMovie(query,
                success = {
                    if (it.isEmpty()) {
                        view.emptyMovies()
                    } else {
                        view.successSearchMovies(it)
                    }
                    view.hideLoading()
                },
                fail = {
                    when (it) {
                        is HttpException -> view.networkError()
                        else -> view.errorMessage(it.message.toString())
                    }
                    view.hideLoading()
                }
            )
        }
    }
}