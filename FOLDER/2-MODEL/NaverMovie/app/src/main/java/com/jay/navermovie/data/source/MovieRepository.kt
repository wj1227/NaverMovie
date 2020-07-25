package com.jay.navermovie.data.source

import com.jay.navermovie.data.Movie

interface MovieRepository {

    fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}