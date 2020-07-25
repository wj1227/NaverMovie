package com.jay.navermovie.data.search.source

import com.jay.navermovie.data.search.Movie

interface MovieRepository {

    fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}