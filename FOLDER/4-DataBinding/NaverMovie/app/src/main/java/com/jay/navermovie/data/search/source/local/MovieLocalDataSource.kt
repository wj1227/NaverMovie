package com.jay.navermovie.data.search.source.local

import com.jay.navermovie.data.search.Movie

interface MovieLocalDataSource {

    fun insertMovies(movies: List<Movie>)
    fun searchMovie(title: String): List<Movie>
    fun getAllMovies(): List<Movie>
    fun deleteAll()

}