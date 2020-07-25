package com.jay.navermovie.data.search.source.remote

import com.jay.navermovie.data.search.Movie

interface MovieRemoteDataSource {

    fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}