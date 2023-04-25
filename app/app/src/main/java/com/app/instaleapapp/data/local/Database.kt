package com.app.instaleapapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1

@Database(
    entities = [
        MovieEntity::class,
        TVShowEntity::class],
    version = DATABASE_VERSION
)
abstract class Database : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun tvShowsDao(): TVShowsDao
}