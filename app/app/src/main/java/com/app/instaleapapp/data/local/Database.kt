package com.app.instaleapapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.instaleapapp.data.local.dao.MoviesDao
import com.app.instaleapapp.data.local.dao.TVShowsDao
import com.app.instaleapapp.data.local.models.MovieDetailsEntity
import com.app.instaleapapp.data.local.models.OnTheAirTVShowEntity
import com.app.instaleapapp.data.local.models.PopularMovieEntity
import com.app.instaleapapp.data.local.models.PopularTVShowEntity
import com.app.instaleapapp.data.local.models.TVShowDetailsEntity
import com.app.instaleapapp.data.local.models.TopRatedMovieEntity

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