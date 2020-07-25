package com.jay.navermovie.data.search.source.local

import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.database.MovieDao
import com.jay.navermovie.data.search.source.local.MovieLocalDataSource

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) :
    MovieLocalDataSource {

    override fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override fun searchMovie(title: String): List<Movie> {
        return movieDao.getSearchTitle(title)
    }

    override fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override fun deleteAll() {
        movieDao.deleteAll()
    }
}