package com.jay.navermovie.data.source.local

import com.jay.navermovie.data.Movie
import com.jay.navermovie.data.source.database.MovieDao

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource{

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