package com.jay.navermovie.data.source.remote

import com.jay.navermovie.data.Movie

interface MovieRemoteDataSource {

    fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}