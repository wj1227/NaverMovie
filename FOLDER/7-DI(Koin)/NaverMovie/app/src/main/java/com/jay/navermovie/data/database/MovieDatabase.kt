package com.jay.navermovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.navermovie.data.search.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}