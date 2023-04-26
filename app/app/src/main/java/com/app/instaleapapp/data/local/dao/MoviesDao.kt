package com.app.instaleapapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.instaleapapp.data.local.models.MovieDetailsEntity
import com.app.instaleapapp.data.local.models.PopularMovieEntity
import com.app.instaleapapp.data.local.models.TopRatedMovieEntity

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