package com.jay.navermovie.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.navermovie.data.search.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<Movie>)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun getSearchTitle(title: String): List<Movie>

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

}