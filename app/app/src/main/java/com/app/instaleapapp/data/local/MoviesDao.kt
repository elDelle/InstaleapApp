package com.app.instaleapapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MoviesDao {

    @Upsert
    suspend fun updateOrInsertPopularMovies(popularMovie: PopularMovieEntity)

    @Query(value = "SELECT * FROM PopularMovieEntity")
    suspend fun getPopularMovies(): List<PopularMovieEntity>

    @Upsert
    suspend fun updateOrInsertTopRatedMovies(topRatedMovies: TopRatedMovieEntity)

    @Query(value = "SELECT * FROM TopRatedMovieEntity")
    suspend fun getTopRatedMovies(): List<TopRatedMovieEntity>

    @Upsert
    suspend fun updateOrInsertMovieDetails(movieDetails: MovieDetailsEntity)

    @Query(value = "SELECT * FROM MovieDetailsEntity WHERE idMovie = :idMovie")
    suspend fun getMovieDetails(idMovie: Int): MovieDetailsEntity
}