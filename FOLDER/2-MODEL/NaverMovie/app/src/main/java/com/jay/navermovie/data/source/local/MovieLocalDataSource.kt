package com.jay.navermovie.data.source.local

import com.jay.navermovie.data.Movie

interface MovieLocalDataSource {

    fun insertMovies(movies: List<Movie>)
    fun searchMovie(title: String): List<Movie>
    fun getAllMovies(): List<Movie>
    fun deleteAll()

}