package com.app.instaleapapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1

@Database(
    entities = [
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        MovieDetailsEntity::class,
        PopularTVShowEntity::class,
        OnTheAirTVShowEntity::class,
        TVShowDetailsEntity::class],
    version = DATABASE_VERSION
)
abstract class Database : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun tvShowsDao(): TVShowsDao
}